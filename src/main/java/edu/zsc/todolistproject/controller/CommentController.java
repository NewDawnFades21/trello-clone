package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Card;
import edu.zsc.todolistproject.domain.Comment;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.CardMapper;
import edu.zsc.todolistproject.mapper.CommentMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/comment/add")
    public ResponseEntity<Comment> addComment(Comment comment) {
        comment.setCreateTime(new Date());
        User user = userMapper.getUserById(comment.getUserId());
        comment.setUser(user);
        commentMapper.addComment(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

}
