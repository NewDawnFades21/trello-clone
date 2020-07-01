package edu.zsc.todolistproject.controller;

import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.bean.ResponseBean;
import edu.zsc.todolistproject.constants.SortBy;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.UserAuthority;
import edu.zsc.todolistproject.service.UserService;
import edu.zsc.todolistproject.vo.UserAuthorityDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理 后台控制类
 */
@Slf4j
@RestController
@RequestMapping("/backstage/user")
public class BackstageUserController {

    @Autowired
    UserService userService;


    @GetMapping("/queryUserByMap")
    public Object queryUserByMap(@RequestParam(defaultValue = "10") int pageSize,
                                 @RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam String username,
                                 @RequestParam String email,
                                 @RequestParam Long authorityId){
        log.info("分页请求*"+pageNum+";"+pageSize+","+authorityId+","+username+","+email);
        PageDomain pageDomain=new PageDomain(pageNum,pageSize,"id", SortBy.DESC);
        PageInfo<UserAuthorityDetail> pageInfo=userService.queryUserByUsernameAuthrityEmailDate(pageDomain,username,email,authorityId);
        return ResponseBean.success(pageInfo);
    }

    @GetMapping("/viewUserById/{id}")
    public Object queryUserById(@PathVariable Long id){
        log.info("用户id:"+id);
        UserAuthorityDetail userAuthorityDetail=userService.queryUserById(id);
        return ResponseBean.success(userAuthorityDetail);
    }

    @PostMapping("/editUserById")
    public Object editUserById(@RequestBody User user){
        log.info("用户:"+user);
        Integer updateCount=userService.updateUser(user);
        return ResponseBean.success(updateCount);
    }

    @GetMapping("/deleteUserById/{id}")
    public Object deleteUserById(@PathVariable Long id){
        log.info("用户id:"+id);
        Integer deleteCount=userService.deleteUserById(id);
        log.info("删除数量:"+deleteCount);
        return ResponseBean.success(deleteCount);
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody UserAuthorityDetail userAuthorityDetail){
        System.out.println(userAuthorityDetail+"用户详情类");
        Integer addCount=userService.insertUser(userAuthorityDetail);
        return ResponseBean.success(addCount);
    }

    @GetMapping("/viewAuthorityExcludeUserByuserId/{userId}")
    public Object viewAuthorityExcludeUserByuserId(@PathVariable Long userId){
        log.info("用户id:"+userId);
        List<Authority> authorityList=userService.viewAuthorityExcludeUserByuserId(userId);
        return ResponseBean.success(authorityList);
    }

    @GetMapping("/viewAuthorityContainUserByuserId/{userId}")
    public Object viewAuthorityContainUserByuserId(@PathVariable Long userId){
        log.info("用户id:"+userId);
        List<Authority> authorityList=userService.viewAuthorityContainUserByuserId(userId);
        return ResponseBean.success(authorityList);
    }

    @PostMapping("/addUserAuthority")
    public Object addUserAuthority(@RequestBody UserAuthority userAuthority){
        log.info("用户权限id#:"+userAuthority);
        Integer addCount=userService.addUserAuthority(userAuthority);
        return ResponseBean.success(addCount);
    }

    @PostMapping("/deleteUserAuthority")
    public Object deleteUserAuthority(@RequestBody UserAuthority userAuthority){
        log.info("用户权限id:**"+userAuthority);
        Integer deleteCount=userService.deleteUserAuthority(userAuthority);
        return ResponseBean.success(deleteCount);
    }

    @PostMapping("/activateUsers")
    public Object activateUsers(@RequestBody List<Long> userIds){
        log.info("用户ids:"+userIds);
        Integer activateCount=userService.activateUsers(userIds);
        return ResponseBean.success(activateCount);
    }

    @PostMapping("/stopUsers")
    public Object stopUsers(@RequestBody List<Long> userIds){
        log.info("用户ids:"+userIds);
        Integer stopCount=userService.stopUsers(userIds);
        return ResponseBean.success(stopCount);
    }

    @PostMapping("/deleteByIds")
    public Object deleteByIds(@RequestBody List<Long> userIds){
        log.info("用户ids:"+userIds);
        Integer deleteCount=userService.deleteByIds(userIds);
        return ResponseBean.success(deleteCount);
    }

    @PostMapping("/resetPasswords")
    public Object resetPasswords(@RequestBody List<Long> userIds){
        log.info("用户ids:"+userIds);
        Integer resetCount=userService.resetPasswords(userIds);
        return ResponseBean.success(resetCount);
    }

    //    @GetMapping("/userAll")
//    public Object querryUserAll(@RequestParam(defaultValue = "10") int pageSize,
//                                @RequestParam(defaultValue = "1") int pageNum,
//                                @RequestParam(defaultValue = "") String username,
//                                @RequestParam(defaultValue = "") String email,
//                                @RequestParam Long authorityId){
//        log.info("分页请求"+pageNum+";"+pageSize);
//        PageDomain pageDomain=new PageDomain(pageNum,pageSize,"id", SortBy.DESC);
//        PageInfo<UserAuthorityDetail> pageInfo=userService.queryUserAll(pageDomain);
//        return ResponseBean.success(pageInfo);
//    }
}

