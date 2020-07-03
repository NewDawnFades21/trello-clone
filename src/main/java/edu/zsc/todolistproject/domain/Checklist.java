package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Checklist implements Serializable {
    private Long id;
    private String title;
    private Long cardId;
    private Long userId;
    private User user;
    private int percent;
    private List<ToDoItem> toDoItemList;
}
