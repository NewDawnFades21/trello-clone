package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Card;
import edu.zsc.todolistproject.mapper.CardMapper;
import edu.zsc.todolistproject.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "cards")
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper cardMapper;

    @Cacheable(key = "'card_'.concat(#id)",unless = "#result==null")
    @Override
    public Card getCardById(Long id) {
        return cardMapper.getCardById(id);
    }

    @CacheEvict(key = "'card_'.concat(#id)")
    @Override
    public int deleteCardById(Long id) {
        return cardMapper.deleteCardById(id);
    }

    @Override
    public int insertCard(Card card) {
        return cardMapper.insertCard(card);
    }

    @CachePut(key = "'card_'.concat(#card.id)",unless = "#result==0")
    @Override
    public int updateCard(Card card) {
        return cardMapper.updateCard(card);
    }

//    @CachePut(key = "'card_'.concat(#title)",unless = "#result==0")
//    @Override
//    public int updateCardTitle(Long id, String title) {
//        return 0;
//    }
//
//
//    @Override
//    public int updateCardDesc(Long id, String description) {
//        return 0;
//    }
//
//    @Override
//    public List<Card> getCards() {
//        return null;
//    }

    @Cacheable(key = "'card_deck_'.concat(#deckId)",unless = "#result==null")
    @Override
    public List<Card> getCardsByDeck(Long deckId) {
        return cardMapper.getCardsByDeck(deckId);
    }
}
