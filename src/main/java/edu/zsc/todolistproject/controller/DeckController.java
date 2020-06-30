package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Deck;
import edu.zsc.todolistproject.mapper.DeckMapper;
import edu.zsc.todolistproject.mapper.BoardMapper2;
import edu.zsc.todolistproject.service.DeckService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class DeckController {
    @Autowired
    private DeckService deckService;

    @Autowired
    private BoardMapper2 boardMapper2;

    @PostMapping("/deck/add")
    public ResponseEntity<?> addDeck(String title, long boardId) {
        Deck deck = new Deck();
        deck.setTitle(title);
        deck.setBoardId(boardId);
        int i = deckService.insertDeck(deck);
        if (i == 1)
            return new ResponseEntity<>(deck, HttpStatus.OK);
        return new ResponseEntity<>("未知錯誤",new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/deck/update")
    public ResponseEntity<?> updateDeck(Deck deck) {
        deckService.updateDeck(deck);
        return new ResponseEntity<String>("添加成功", HttpStatus.OK);
    }

    @DeleteMapping("/deck/delete/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable("id") Long id){
        int res = deckService.deleteDeckById(id);
        if (res == 1)
            return new ResponseEntity<>("删除成功",HttpStatus.OK);
        else
            return new ResponseEntity<>("删除失败",new HttpHeaders(),HttpStatus.BAD_REQUEST );
    }
// -----------------------   李-----------------------------
    @GetMapping("/deck")
    public String deckview(@RequestParam Long id, Model model) {
        Board board= boardMapper2.findBoardById(id);
        model.addAttribute("board",board);
        return "/notebook/deck";
    }

    //创建一个新面板
    @PostMapping("/deck")
    public String addBoard(@RequestParam String title, @RequestParam int visibility, @SessionAttribute User loginUser, Model model){
        Date date=new Date();
        Timestamp submitDate=new Timestamp(date.getTime());
        //改：加上teamId
        Board board=new Board();
        board.setTitle(title);
        board.setVisibility(visibility);
        board.setUserId(loginUser.getId());
        board.setSubmitDate(submitDate);
        boardMapper2.insertBoard(board);
        Long id=board.getId();
        model.addAttribute("id",id);
        return "redirect:/notebook/deck";
    }

}
