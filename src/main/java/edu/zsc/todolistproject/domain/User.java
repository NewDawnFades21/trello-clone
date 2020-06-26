package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String introduction;
    private Date registerDate;
    private List<String> authorities;
    private String email;
}
