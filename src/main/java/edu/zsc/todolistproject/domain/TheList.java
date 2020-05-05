package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class TheList {
    private Integer id;
    private Board board;
    private List<ToDoItem> checklists;
}
