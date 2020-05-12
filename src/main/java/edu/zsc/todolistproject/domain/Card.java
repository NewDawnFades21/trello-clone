package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class Card {
    private Integer id;
    private String title;
    private String description;
    private String comment;
    private List<Checklist> checklists;
}
