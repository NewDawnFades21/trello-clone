package edu.zsc.todolistproject.service;


import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeamService {


     List<Team> findUserTeam(Long userId);

     //个人有哪些团队 select用
     public List<Team> findPersonalTeam(Long userId);

     public List<TeamType> findTeamType();

}
