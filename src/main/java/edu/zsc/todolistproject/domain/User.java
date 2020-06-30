package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String introduction;
    private Date registerDate;
}
