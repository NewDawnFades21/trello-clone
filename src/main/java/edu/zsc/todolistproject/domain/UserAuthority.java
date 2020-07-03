package edu.zsc.todolistproject.domain;

import lombok.Data;

@Data
public class UserAuthority {
    private Long id;
    private Long userId;
    private Long authorityId;
}
