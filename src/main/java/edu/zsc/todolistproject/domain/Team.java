package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Long id;
    private String title;
    private int typeId;
    private String description;
    private List<Board> boardList;

}
