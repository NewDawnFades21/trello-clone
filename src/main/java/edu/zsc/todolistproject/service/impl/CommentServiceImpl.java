package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Comment;
import edu.zsc.todolistproject.mapper.CommentMapper;
import edu.zsc.todolistproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@CacheConfig(cacheNames = "comments")
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Cacheable(key = "'comments_card'.concat(#cardId)",unless = "#result==null")
    @Override
    public List<Comment> getCommentsByCardId(Long cardId) {
        return commentMapper.getCommentsByCardId(cardId);
    }

    @Cacheable(key = "'comment_'.concat(#id)",unless = "#result==null")
    @Override
    public Comment getCommentById(Long id) {
        return commentMapper.getCommentById(id);
    }

    @CachePut(key = "'comment_'.concat(#comment.id)",unless = "#result==0")
    @Override
    public int editComment(Comment comment) {
        return commentMapper.editComment(comment);
    }

    @CacheEvict(key = "'comment_'.concat(#id)")
    @Override
    public int deleteComment(Long id) {
        return commentMapper.deleteComment(id);
    }
}
