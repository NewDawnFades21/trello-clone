package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {
    private Long id;
    private String content;
    private Date createTime;
    private Long cardId;
    private Long userId;
    private User user;
    private Boolean modified;
}
