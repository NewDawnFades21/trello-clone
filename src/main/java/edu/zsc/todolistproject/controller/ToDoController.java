package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Checklist;
import edu.zsc.todolistproject.domain.ToDoItem;
import edu.zsc.todolistproject.mapper.ChecklistMapper;
import edu.zsc.todolistproject.mapper.ToDoMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.ChecklistService;
import edu.zsc.todolistproject.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    @Autowired
    ToDoService toDoService;
    @Autowired
    ChecklistService checklistService;

    public int getPercent(List<ToDoItem> items) {
        int itemsCount = items.size();
        int isDoneCount = 0;
        if (items != null) {
            for (ToDoItem toDoItem : items) {
                if (toDoItem.getIsDone()) {
                    isDoneCount++;
                }
            }
        }
        return Math.round(100 * ((float) isDoneCount / (float) itemsCount));
    }

    @PostMapping("/buildToDoTable")
    public String buildToDoTable(@RequestParam(value = "checklistId", required = false) Long checklistId, Model model) {
        Checklist checklist = new Checklist();
        if (checklistId != null) {
            checklist = checklistService.getChecklistById(checklistId);
            List<ToDoItem> toDoItems = toDoService.getToDoItemsByChecklistId(checklistId);
            checklist.setToDoItemList(toDoItems);
        }
        model.addAttribute("checklist", checklist);
        return "fragments/toDoTable :: todotable";
    }

    @PutMapping("/ajaxEdit")
    public String ajaxEdit(ToDoItem toDoItem, Model model) {
        toDoService.updateToDoItem(toDoItem);
        List<ToDoItem> items = toDoService.getToDoItemsByChecklistId(toDoItem.getChecklistId());
        int percent = getPercent(items);
        Checklist checklist = checklistService.getChecklistById(toDoItem.getChecklistId());
        checklistService.updateChecklistPercent(percent, toDoItem.getChecklistId());
        checklist.setToDoItemList(items);
        checklist.setPercent(percent);
        model.addAttribute("checklist", checklist);
        return "fragments/toDoTable :: todotable";
    }

    @PostMapping("/ajaxCreate")
    public String ajaxCreate(ToDoItem toDoItem, Model model) {
        toDoService.insertToDoItem(toDoItem);
        List<ToDoItem> items = toDoService.getToDoItemsByChecklistId(toDoItem.getChecklistId());
        Checklist checklist = checklistService.getChecklistById(toDoItem.getChecklistId());
        checklist.setToDoItemList(items);
        checklist.setPercent(getPercent(items));
        model.addAttribute("checklist", checklist);
        return "fragments/toDoTable :: todotable";
    }
}
