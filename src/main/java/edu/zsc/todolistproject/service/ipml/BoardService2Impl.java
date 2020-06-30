package edu.zsc.todolistproject.service.ipml;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.mapper.BoardMapper2;
import edu.zsc.todolistproject.mapper.TeamMapper;
import edu.zsc.todolistproject.service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService2")
@Transactional
@CacheConfig(cacheNames = "board")
public class BoardService2Impl implements BoardService2 {

    @Autowired
    BoardMapper2 boardMapper;

    Board board;
    List<Board> list;


    @Override
    @Cacheable(unless = "#result==null")
    public Board findBoardById(Long id) {
        board=boardMapper.findBoardById(id);
        return board;
    }

    @Override
    public void insertBoard(Board board) {
         boardMapper.insertBoard(board);
    }

    @Override
    @Cacheable(unless = "#result==null")
    public List<Board> findAllBoard(Long userId) {
        list=boardMapper. findAllBoard(userId);
        return list;
    }

    @Override
    @Cacheable(unless = "#result==null")
    public List<Board> findBoardSubmitDate(Long userId) {
        list=boardMapper.findBoardSubmitDate(userId);
        return list;
    }

    @Override
    @Cacheable(unless = "#result==null")
    public List<Board> findPeraonalTeamBoard(Long userId, Long teamId) {
        list=boardMapper.findPeraonalTeamBoard(userId,teamId);
        return list;
    }

    @Override
    @Cacheable(unless = "#result==null")
    public List<Board> findAllTeamBoard(Long userId, Long teamId) {
        list=boardMapper.findAllTeamBoard(userId,teamId);
        return list;
    }



    @Override
    public void insertTeamMember(Long teamId, Long userId) {
         boardMapper.insertTeamMember(teamId,userId);
    }

    @Override
    public void insertTeam(Team team) {
        boardMapper.insertTeam(team);

    }

    @Override
    @Cacheable(unless = "#result==null")
    public Long findUserId(String username) {
        Long id=boardMapper.findUserId(username);
        return id;
    }

    @Override
    public void insertBoardNoTeam(Board board) {
        boardMapper.insertBoardNoTeam(board);
    }
}
