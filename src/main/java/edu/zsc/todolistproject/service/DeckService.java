package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Deck;

import java.util.List;

public interface DeckService {
    Deck getDeckById(Long id);

    int deleteDeckById(Long id);

    int insertDeck(Deck deck);

    int updateDeck(Deck deck);

    List<Deck> getDecksByBoard(Long boardId);
}
