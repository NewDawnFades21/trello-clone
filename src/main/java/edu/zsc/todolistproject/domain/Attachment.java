package edu.zsc.todolistproject.domain;

import lombok.Data;

import java.util.Date;

//要用包装类，不然会报错
@Data
public class Attachment {
    private Long id;
    private String filename;
    private String path;
    private Long userId;
    private Long cardId;
    private Date createTime;
}
