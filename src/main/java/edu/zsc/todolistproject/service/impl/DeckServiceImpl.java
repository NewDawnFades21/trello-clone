package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Deck;
import edu.zsc.todolistproject.mapper.DeckMapper;
import edu.zsc.todolistproject.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@CacheConfig(cacheNames = "decks")
public class DeckServiceImpl implements DeckService {
    @Autowired
    private DeckMapper deckMapper;
    @Cacheable(key = "'deck_'.concat(#id)",unless = "#result==null")
    @Override
    public Deck getDeckById(Long id) {
        return deckMapper.getDeckById(id);
    }

    @CacheEvict(key = "'deck_'.concat(#id)")
    @Override
    public int deleteDeckById(Long id) {
        return deckMapper.deleteDeckById(id);
    }

    @Override
    public int insertDeck(Deck deck) {
        return deckMapper.insertDeck(deck);
    }

    @CachePut(key = "'deck_'.concat(#deck.id)")
    @Override
    public int updateDeck(Deck deck) {
        return deckMapper.updateDeck(deck);
    }


    @Cacheable(key = "'decks_board'.concat(#boardId)",unless = "#result==null")
    @Override
    public List<Deck> getDecksByBoard(Long boardId) {
        return deckMapper.getDecksByBoard(boardId);
    }
}
