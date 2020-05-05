package edu.zsc.todolistproject.domain;

import lombok.Data;

@Data
public class Board {
    private Integer id;
    private String title;
    private boolean visibility;
    private User user;
    private Team team;
}
