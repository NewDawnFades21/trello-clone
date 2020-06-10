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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
        comment.setModified(false);
        User user = userMapper.getUserById(comment.getUserId());
        comment.setUser(user);
        commentMapper.addComment(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PutMapping("/comment/edit")
    public ResponseEntity<?> editComment(@RequestBody Map<String,Object> map){
        Comment comment = commentMapper.getCommentById(Long.parseLong((String) map.get("id")));
        comment.setContent((String) map.get("content"));
        comment.setModified(true);
        int res = commentMapper.editComment(comment);
        if (res == 1){
            return new ResponseEntity<>(1,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(0,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        int res = commentMapper.deleteComment(id);
        if (res == 1){
            return new ResponseEntity<>(1,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(0,HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
