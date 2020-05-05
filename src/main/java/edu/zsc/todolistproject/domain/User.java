package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String introduction;
    private Date registerDate;
    private List<Team> teamList;
}
