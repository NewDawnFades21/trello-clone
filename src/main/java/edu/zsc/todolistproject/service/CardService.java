package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CardService {
    Card getCardById(Long id);

    int deleteCardById(Long id);

    int insertCard(Card card);

    int updateCard(Card card);

//    int updateCardTitle(Long id, String title);
//
//    int updateCardDesc(Long id,String description);

//    List<Card> getCards();

    List<Card> getCardsByDeck(Long deckId);
}
