package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Deck;
import edu.zsc.todolistproject.mapper.DeckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class DeckController {
    @Autowired
    private DeckMapper deckMapper;

    @PostMapping("/deck/add")
    public ResponseEntity<?> addDeck(String title, long boardId) {
        Deck deck = new Deck();
        deck.setTitle(title);
        deck.setBoardId(boardId);
        deckMapper.insertDeck(deck);
        return new ResponseEntity<String>("添加成功", HttpStatus.OK);
    }

    @PutMapping("/deck/update")
    public ResponseEntity<?> updateDeck(Deck deck) {
        deckMapper.updateDeck(deck);
        return new ResponseEntity<String>("添加成功", HttpStatus.OK);
    }
}
