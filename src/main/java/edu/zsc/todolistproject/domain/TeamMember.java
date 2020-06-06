package edu.zsc.todolistproject.domain;

import lombok.Data;

@Data
public class TeamMember {
    Long id;
    Long teamId;
    Long userId;
}