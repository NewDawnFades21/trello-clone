package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Board {
    private Long id;
    private String title;
    private int visibility;
    private Long userId;
    private Map<Long, Deck> decks = new LinkedHashMap<>();
}
