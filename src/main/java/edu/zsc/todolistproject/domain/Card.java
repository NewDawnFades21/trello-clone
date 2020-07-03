package edu.zsc.todolistproject.domain;

import lombok.Data;

import javax.swing.text.AbstractDocument;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class Card implements Serializable {
    private Long id;
    private Long deckId;
    private String title;
    private String description;
    private List<Comment> comments = new LinkedList<>();
    private List<Checklist> checklists = new LinkedList<>();
    private List<Attachment> attachments = new LinkedList<>();
}
