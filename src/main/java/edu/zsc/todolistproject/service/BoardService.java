package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Board;

import java.util.List;

public interface BoardService {
    Board getBoardById(Long id);

    int deleteBoardById(Long id);

    int insertBoard(Board board);

    int updateBoard(Board board);
}
