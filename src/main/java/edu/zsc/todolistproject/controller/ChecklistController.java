package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.auth.MySessionInfo;
import edu.zsc.todolistproject.domain.Checklist;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.ChecklistMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.ChecklistService;
import edu.zsc.todolistproject.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;
    @Autowired
    private ToDoService toDoService;
    @Autowired
    private MySessionInfo mySessionInfo;
    @PostMapping("/checklist/add")
    public ResponseEntity<Checklist> addChecklist(String title, long cardId) {
        User user = mySessionInfo.getCurrentUser();
        Checklist checklist = new Checklist();
        checklist.setTitle(title);
        checklist.setCardId(cardId);
        checklist.setUserId(user.getId());
        checklist.setPercent(0);
        checklist.setUser(user);
        checklistService.addChecklist(checklist);

        return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
    }

    @DeleteMapping("/checklist/delete/{id}")
    public ResponseEntity<?> deleteChecklist(@PathVariable("id") Long id){
        toDoService.deleteToDoItemByChecklistId(id);
        int res = checklistService.deleteChecklistById(id);
        if (res == 1)
            return new ResponseEntity<>("删除成功",HttpStatus.OK);
        return new ResponseEntity<>("删除失败",new HttpHeaders(),HttpStatus.BAD_REQUEST );
    }

}
