package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.auth.MySessionInfo;
import edu.zsc.todolistproject.domain.Card;
import edu.zsc.todolistproject.domain.Comment;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.CardMapper;
import edu.zsc.todolistproject.mapper.CommentMapper;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.CommentService;
import edu.zsc.todolistproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MySessionInfo mySessionInfo;

    @PostMapping("/comment/add")
    public ResponseEntity<Comment> addComment(HttpServletRequest request,Comment comment) {
        comment.setCreateTime(new Date());
//        comment.setContent(HtmlUtils.htmlEscape(comment.getContent())); 不要这个
        comment.setModified(false);
        comment.setUser(mySessionInfo.getCurrentUser());
        comment.setUserId(mySessionInfo.getCurrentUser().getId());
        comment.setModified(false);
        commentService.addComment(comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PutMapping("/comment/edit")
    public ResponseEntity<?> editComment(@RequestBody Map<String,Object> map){
        long id = Long.parseLong((String)map.get("id"));
        Comment comment = commentService.getCommentById(id);
        if (comment.getUserId()!=mySessionInfo.getCurrentUser().getId()){
            return new ResponseEntity<>("不能修改別的用戶評論哦",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        comment.setContent((String) map.get("content"));
        comment.setModified(true);
        int res = commentService.editComment(comment);
        if (res == 1){
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("未知错误！",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        Comment comment = commentService.getCommentById(id);
        Long userId = comment.getUserId();
        if (mySessionInfo.getCurrentUser().getId()!=userId){
            return new ResponseEntity<>("不能删除別的用戶評論哦",new HttpHeaders(),HttpStatus.BAD_REQUEST );
        }
        int res = commentService.deleteComment(id);
        if (res == 1){
            return new ResponseEntity<>("删除成功",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("删除失败",HttpStatus.BAD_REQUEST);
        }
    }

}
