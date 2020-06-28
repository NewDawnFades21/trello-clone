package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.auth.MySessionInfo;
import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Card;
import edu.zsc.todolistproject.domain.Deck;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.BoardMapper;
import edu.zsc.todolistproject.mapper.CardMapper;
import edu.zsc.todolistproject.mapper.DeckMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.BoardService;
import edu.zsc.todolistproject.service.CardService;
import edu.zsc.todolistproject.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private DeckService deckService;
    @Autowired
    private CardService cardService;
    @Autowired
    private MySessionInfo mySessionInfo;

    @RequestMapping("/board/{id}")
    public String board(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoardById(id);
        List<Deck> decks = deckService.getDecksByBoard(board.getId());
        for (Deck deck : decks) {
            board.getDecks().put(deck.getId(), deck);
            List<Card> cardList = cardService.getCardsByDeck(deck.getId());
            for (Card card : cardList) {
                board.getDecks().get(deck.getId()).getCards().put(card.getId(), card);
            }
        }
        model.addAttribute("board", board);
        model.addAttribute("user", mySessionInfo.getCurrentUser());
        return "board";
    }

    @PutMapping("/board")
    public ResponseEntity<?> updateBoardTitle(Board board){
        Board boardOld = boardService.getBoardById(board.getId());
        boardOld.setTitle(board.getTitle());
        int i = boardService.updateBoard(board);
        if (i == 1)
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        return new ResponseEntity<>("修改失敗",new HttpHeaders(),HttpStatus.BAD_REQUEST );
    }
}
