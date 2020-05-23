package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Checklist;
import edu.zsc.todolistproject.mapper.ChecklistMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChecklistController {

    @Autowired
    private ChecklistMapper checklistMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/checklist/add")
    public ResponseEntity<Checklist> addChecklist(long userId, String title, long cardId) {
        Checklist checklist = new Checklist();
        checklist.setTitle(title);
        checklist.setCardId(cardId);
        checklist.setUserId(userId);
        checklist.setPercent(0);
        checklist.setUser(userMapper.getUserById(userId));
        checklistMapper.addChecklist(checklist);

        return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
    }

}
