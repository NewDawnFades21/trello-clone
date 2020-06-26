package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Comment;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);

    List<Comment> getCommentsByCardId(Long cardId);

    Comment getCommentById(Long id);

    int editComment(Comment comment);

    int deleteComment(Long id);
}
