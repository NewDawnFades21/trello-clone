package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Card;
import edu.zsc.todolistproject.domain.Deck;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.BoardMapper;
import edu.zsc.todolistproject.mapper.CardMapper;
import edu.zsc.todolistproject.mapper.DeckMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private DeckMapper deckMapper;
    @Autowired
    private CardMapper cardMapper;

    @RequestMapping("{userId}/board/{id}")
    public String board(@PathVariable("userId") Long userId,@PathVariable("id") Long id, Model model) {
        User user = userMapper.getUserById(userId);
        Board board = boardMapper.getBoardById(id);
        List<Deck> decks = deckMapper.getDecksByBoard(board.getId());
        for (Deck deck : decks) {
            board.getDecks().put(deck.getId(), deck);
            List<Card> cardList = cardMapper.getCardsByDeck(deck.getId());
            for (Card card : cardList) {
                board.getDecks().get(deck.getId()).getCards().put(card.getId(), card);
            }
        }
        model.addAttribute("board", board);
        model.addAttribute("user", user);
        return "board";
    }
}
