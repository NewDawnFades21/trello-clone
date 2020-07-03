package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardService2 {

    public Board findBoardById(Long id);

    public void insertBoard(Board board);

    public List<Board> findAllBoard(Long userId);

    public List<Board> findBoardSubmitDate(Long userId);

    public List<Board> findPeraonalTeamBoard(Long userId, Long teamId);

    //所有团队面板
    public List<Board> findAllTeamBoard(Long userId, Long teamId);

    public void insertTeamMember(Long teamId,Long userId);

    public void insertTeam(Team team);

    public Long findUserId(String username);

    public void insertBoardNoTeam(Board board);

}
