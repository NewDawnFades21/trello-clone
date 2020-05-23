package edu.zsc.todolistproject.domain;

import lombok.Data;

@Data
public class Attachment {
    private long id;
    private String filename;
    private String path;
    private long userId;
    private long cardId;
}
