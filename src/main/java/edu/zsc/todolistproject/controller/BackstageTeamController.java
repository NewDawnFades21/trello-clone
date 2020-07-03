package edu.zsc.todolistproject.controller;

import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.bean.ResponseBean;
import edu.zsc.todolistproject.constants.SortBy;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.service.TeamService;
import edu.zsc.todolistproject.vo.TeamDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/backstage/team")
public class BackstageTeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/teamTypeAll")
    public Object querryTeamTypeAll(@RequestParam(defaultValue = "10") int pageSize,
                                @RequestParam(defaultValue = "1") int pageNum){
        log.info("分页请求"+pageNum+";"+pageSize);
        PageDomain pageDomain=new PageDomain(pageNum,pageSize,"id", SortBy.DESC);
        PageInfo<TeamType> pageInfo=teamService.queryTeamTypeAll(pageDomain);
        return ResponseBean.success(pageInfo);
    }

    @GetMapping("/teamAll")
    public Object queryTeamAll(@RequestParam(defaultValue = "10") int pageSize,
                               @RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam String username,
                               @RequestParam String title,
                               @RequestParam Long typeId
                               ){
        log.info("分页请求"+pageNum+";"+pageSize+","+username+","+title+","+typeId);
        PageDomain pageDomain=new PageDomain(pageNum,pageSize,"id", SortBy.DESC);
        PageInfo<TeamDetail> pageInfo=teamService.queryTeamAll(pageDomain,username,title,typeId);
        return ResponseBean.success(pageInfo);
    }

    @PostMapping("/addTeamType")
    public Object addTeamType(@RequestBody TeamType teamType){
        log.info("团队类型名："+teamType);
        Integer addCount=teamService.addTeamType(teamType.getName());
        return ResponseBean.success(addCount);
    }

    @GetMapping("/viewTeamTypeById/{teamTypeId}")
    public Object viewTeamTypeById(@PathVariable Long teamTypeId){
        log.info("团队类型id:"+teamTypeId);
        TeamType teamType=teamService.queryTeamTypeById(teamTypeId);
        return ResponseBean.success(teamType);
    }

    @PostMapping("/editTeamType")
    public Object editTeamType(@RequestBody TeamType teamType){
        log.info("编辑团队类型："+teamType);
        Integer editCount=teamService.editTeamType(teamType);
        return ResponseBean.success(editCount);
    }

    @GetMapping("/deleteTeamTypeById/{teamTypeId}")
    public Object deleteTeamTypeById(@PathVariable Long teamTypeId){
        log.info("删除团队类型的id:"+teamTypeId);
        Integer deleteCount=teamService.deleteTeamTypeById(teamTypeId);
        return ResponseBean.success(deleteCount);
    }

    @GetMapping("/viewTeamById/{teamId}")
    public Object viewTeamById(@PathVariable Long teamId){
        log.info("团队id:"+teamId);
        TeamDetail teamDetail=teamService.viewTeamById(teamId);
        return ResponseBean.success(teamDetail);
    }

    @PostMapping("/addTeam")
    public Object addTeam(@RequestBody TeamDetail teamDetail){
        log.info("添加团队："+teamDetail);
        Integer addCount=teamService.addTeam(teamDetail);
        return ResponseBean.success(addCount);
    }

    @PostMapping("/editTeam")
    public Object editTeam(@RequestBody TeamDetail teamDetail){
        log.info("编辑团队："+teamDetail);
        Integer editCount=teamService.editTeam(teamDetail);
        return ResponseBean.success(editCount);
    }

    @GetMapping("/deleteTeamById/{teamId}")
    public Object deleteTeamById(@PathVariable Long teamId){
        log.info("删除团队："+teamId);
        Integer deleteCount=teamService.deleteTeamById(teamId);
        return ResponseBean.success(deleteCount);
    }
}
