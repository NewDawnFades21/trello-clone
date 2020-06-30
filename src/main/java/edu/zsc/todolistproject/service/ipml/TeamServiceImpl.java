package edu.zsc.todolistproject.service.ipml;

import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.zsc.todolistproject.service.TeamService;

import java.util.List;

@Service("teamService")
@Transactional
@CacheConfig(cacheNames = "team")
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamMapper teamMapper;


    @Override
    @Cacheable(unless = "#result==null")
    public List<Team> findUserTeam(Long userId) {
        List<Team> teamlist=teamMapper.findUserTeam(userId);
        return teamlist;
    }

    @Override
    @Cacheable(unless = "#result==null")
    public List<Team> findPersonalTeam(Long userId) {
        List<Team>list2=teamMapper.findPersonalTeam(userId);
        return list2;
    }

    @Override
    @Cacheable(cacheNames = "teamType",unless = "#result==null")
    public List<TeamType> findTeamType() {
        List<TeamType>list3=teamMapper.findTeamType();
        return list3;
    }

}
