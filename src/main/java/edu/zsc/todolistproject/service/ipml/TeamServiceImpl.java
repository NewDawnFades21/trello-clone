package edu.zsc.todolistproject.service.ipml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.mapper.TeamMapper;
import edu.zsc.todolistproject.vo.TeamDetail;
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

    //后台管理
    @Override
    public PageInfo<TeamType> queryTeamTypeAll(PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderStr());
        return new PageInfo<>(teamMapper.queryTeamTypeAll());
    }

    @Override
    public PageInfo<TeamDetail> queryTeamAll(PageDomain pageDomain, String username, String title, Long typeId) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderStr());
        return new PageInfo<>(teamMapper.queryTeamDetailByMap(username,title,typeId));
    }

    @Override
    public Integer addTeamType(String teamType) {
        return teamMapper.addTeamType(teamType);
    }

    @Override
    public TeamType queryTeamTypeById(Long id) {
        return teamMapper.queryTeamTypeById(id);
    }

    @Override
    public Integer editTeamType(TeamType teamType) {
        return teamMapper.editTeamType(teamType);
    }

    @Override
    public Integer deleteTeamTypeById(Long teamTypeId) {
        Integer deleteCount =0;
        deleteCount +=teamMapper.deleteTeamMemberByTeamTypeId(teamTypeId);
        System.out.println(deleteCount+"*");
        deleteCount +=teamMapper.deleteTeamByTeamTypeId(teamTypeId);
        System.out.println(deleteCount+"**");
        deleteCount +=teamMapper.deleteTeamTypeByTeamTypeId(teamTypeId);
        System.out.println(deleteCount+"***");
        return deleteCount;
    }

    @Override
    public TeamDetail viewTeamById(Long teamId) {
        return teamMapper.queryTeamDetailByTeamId(teamId);
    }

    @Override
    public Integer addTeam(TeamDetail teamDetail) {
        return teamMapper.addTeam(teamDetail);
    }

    @Override
    public Integer editTeam(TeamDetail teamDetail) {
        return teamMapper.editTeam(teamDetail);
    }

    @Override
    public Integer deleteTeamById(Long teamId) {
        Integer deleteCount=0;
        deleteCount+=teamMapper.deleteTeamMemberByTeamId(teamId);
        deleteCount+=teamMapper.deleteTeamByTeamId(teamId);
        return deleteCount;
    }
    //后台管理**
}
