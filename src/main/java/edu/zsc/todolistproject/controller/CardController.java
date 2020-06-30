package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.*;
import edu.zsc.todolistproject.mapper.*;
import edu.zsc.todolistproject.service.*;
import javafx.scene.control.CheckMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardController {
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ChecklistService checklistService;
    @Autowired
    private ToDoService toDoService;
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/card/add")
    public ResponseEntity<Card> addCard(long deckId, String title) {
        Card card = new Card();
        card.setTitle(title);
        card.setDeckId(deckId);
        cardService.insertCard(card);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @GetMapping("/getCardInfo")
    public ResponseEntity<?> getCardInfo(@RequestParam Long cardId) {
        Card card = null;
        try{
            card = getCardById(cardId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    private Card getCardById(Long cardId) {
        Card card = cardService.getCardById(cardId);
        List<Comment> comments = commentService.getCommentsByCardId(cardId);
        for (Comment comment : comments) {
            User user = userService.getUserById(comment.getUserId());
            comment.setUser(user);
        }
        card.setComments(comments);
        List<Checklist> checklists = checklistService.getChecklistsByCardId(cardId);
        for (Checklist checklist : checklists) {
            User user = userService.getUserById(checklist.getUserId());
            checklist.setUser(user);
            List<ToDoItem> toDoItemList = toDoService.getToDoItemsByChecklistId(checklist.getId());
            checklist.setToDoItemList(toDoItemList);
        }
        card.setChecklists(checklists);
        List<Attachment> attachments = attachmentService.getAttachmentsByCardId(cardId);
        card.setAttachments(attachments);

        return card;
    }

    @PutMapping("/card/update/title")
    public ResponseEntity<?> updateCardTitle(long id, String title) {
        Card card = cardService.getCardById(id);
        card.setTitle(title);
        int i = cardService.updateCard(card);
        if (i == 1)
            return new ResponseEntity<>(card, HttpStatus.OK);
        return new ResponseEntity<>("未知錯誤",new HttpHeaders(),HttpStatus.BAD_REQUEST );

    }

    @PutMapping("/card/update/desc")
    public ResponseEntity<String> updateCardDesc(Long id,String description){
        Card card = cardService.getCardById(id);
        card.setDescription(description);
        cardService.updateCard(card);
        return new ResponseEntity<String>("update card desc success", HttpStatus.OK);

    }

    @DeleteMapping("/card/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id){
        int res = cardService.deleteCardById(id);
        if (res == 1)
            return new ResponseEntity<>("删除成功",HttpStatus.OK);
        return new ResponseEntity<>("删除失败",new HttpHeaders(),HttpStatus.BAD_REQUEST );
    }

}
