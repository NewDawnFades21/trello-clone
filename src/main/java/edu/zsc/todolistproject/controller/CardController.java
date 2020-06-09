package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.*;
import edu.zsc.todolistproject.mapper.*;
import javafx.scene.control.CheckMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ChecklistMapper checklistMapper;
    @Autowired
    private ToDoMapper toDoMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;

    @PostMapping("/card/add")
    public ResponseEntity<Card> addCard(long deckId, String title) {
        Card card = new Card();
        card.setTitle(title);
        card.setDeckId(deckId);
        cardMapper.insertCard(card);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @GetMapping("/getCardInfo")
    public ResponseEntity<?> getCardInfo(@RequestParam Long cardId) {
        Card card = null;
        try{
            card = getCardById(cardId);
        }catch (Exception e){

        }
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    private Card getCardById(Long cardId) {
        Card card = cardMapper.getCardById(cardId);
        List<Comment> comments = commentMapper.getCommentsByCardId(cardId);
        for (Comment comment : comments) {
            User user = userMapper.getUserById(comment.getUserId());
            comment.setUser(user);
        }
        card.setComments(comments);
        List<Checklist> checklists = checklistMapper.getChecklistsByCardId(cardId);
        for (Checklist checklist : checklists) {
            User user = userMapper.getUserById(checklist.getUserId());
            checklist.setUser(user);
            List<ToDoItem> toDoItemList = toDoMapper.getToDoItemsByChecklistId(checklist.getId());
            checklist.setToDoItemList(toDoItemList);
        }
        card.setChecklists(checklists);
        List<Attachment> attachments = attachmentMapper.getAttachmentsByCardId(cardId);
        card.setAttachments(attachments);

        return card;
    }

    @PutMapping("/card/update/title")
    public ResponseEntity<String> updateCardTitle(long id, String title) {
        cardMapper.updateCardTitle(id, title);
        return new ResponseEntity<String>("update card title success", HttpStatus.OK);
    }

    @PutMapping("/card/update/desc")
    public ResponseEntity<String> updateCardDesc(Long id,String description){
        cardMapper.updateCardDesc(id, description);
        return new ResponseEntity<String>("update card desc success", HttpStatus.OK);

    }

}
