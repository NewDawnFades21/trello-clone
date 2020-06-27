package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.mapper.BoardMapper;
import edu.zsc.todolistproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Service
//@CacheConfig(cacheNames = "boards")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;
    @Override
//    @Cacheable(key = "'board_'.concat(#id)",unless = "#result==null")
    public Board getBoardById(Long id) {
        return boardMapper.getBoardById(id);
    }

//    @CacheEvict(key = "'board_'.concat(#id)")
    @Override
    public int deleteBoardById(Long id) {
        return boardMapper.deleteBoardById(id);
    }

    @Override
    public int insertBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

//    @CachePut(key = "'board_'.concat(#board.id)")
    @Override
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

}
