package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamMember implements Serializable {
    Long id;
    Long teamId;
    Long userId;
}