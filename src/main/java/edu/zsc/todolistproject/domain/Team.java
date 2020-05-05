package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private Integer id;
    private String title;
    private TeamType type;
    private String description;
    private List<Board> boardList;

}
