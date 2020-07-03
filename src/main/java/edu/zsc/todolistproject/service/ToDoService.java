package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.ToDoItem;

import java.util.List;

public interface ToDoService {
    ToDoItem getToDoItemById(Long id);

    int deleteToDoItemById(Long id);

    int insertToDoItem(ToDoItem toDoItem);

    int updateToDoItem(ToDoItem toDoItem);

    List<ToDoItem> getToDoItemsByChecklistId(Long checklistId);

    int deleteToDoItemByChecklistId(Long id);
}
