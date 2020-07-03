package edu.zsc.todolistproject.service;


import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.vo.TeamDetail;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamService {


     List<Team> findUserTeam(Long userId);

     //个人有哪些团队 select用
     public List<Team> findPersonalTeam(Long userId);

     public List<TeamType> findTeamType();

     //后台管理
     public PageInfo<TeamType> queryTeamTypeAll(PageDomain pageDomain);
     public PageInfo<TeamDetail> queryTeamAll(PageDomain pageDomain, String username, String title, Long typeId);

     public Integer addTeamType(String teamType);

     public TeamType queryTeamTypeById(Long id);

     public Integer editTeamType(TeamType teamType);

     public Integer deleteTeamTypeById(Long teamTypeId);

     public TeamDetail viewTeamById(Long teamId);

     public Integer addTeam(TeamDetail teamDetail);

     public Integer editTeam(TeamDetail teamDetail);

     public Integer deleteTeamById(Long teamId);
     //后台管理***

}
