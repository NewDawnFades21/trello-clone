package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamType implements Serializable {
    private int id;
    private String name;
}
