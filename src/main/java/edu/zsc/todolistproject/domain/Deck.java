package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Deck {
    private Long id;
    private String title;
    private Long boardId;
    private Map<Long, Card> cards = new LinkedHashMap<>();
}
