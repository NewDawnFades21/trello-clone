package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class Deck {
    private Integer id;
    private Board board;
}
