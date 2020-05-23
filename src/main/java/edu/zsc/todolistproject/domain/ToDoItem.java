package edu.zsc.todolistproject.domain;

import lombok.Data;

@Data
public class ToDoItem {
    private Integer id;
    private String description;
    private boolean isDone;
    private long checklistId;

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }


}
