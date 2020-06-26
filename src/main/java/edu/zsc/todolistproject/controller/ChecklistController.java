package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Checklist;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.ChecklistMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;
    @PostMapping("/checklist/add")
    public ResponseEntity<Checklist> addChecklist(HttpServletRequest request, String title, long cardId) {
        User user = (User) request.getSession().getAttribute("loginUser");
        Checklist checklist = new Checklist();
        checklist.setTitle(title);
        checklist.setCardId(cardId);
        checklist.setUserId(user.getId());
        checklist.setPercent(0);
        checklist.setUser(user);
        checklistService.addChecklist(checklist);

        return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
    }

}
