package edu.zsc.todolistproject.domain;

import lombok.Data;

import javax.swing.text.AbstractDocument;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public class Card {
    private Long id;
    private Long deckId;
    private String title;
    private List<Comment> comments = new LinkedList<>();
    private List<Checklist> checklists = new LinkedList<>();
    private List<Attachment> attachments = new LinkedList<>();
}
