package edu.zsc.todolistproject.controller;

import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.bean.ResponseBean;
import edu.zsc.todolistproject.constants.SortBy;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理 后台控制类
 */
@Slf4j
@RestController
@RequestMapping("/backstage/authority")
public class BackstageAuthorityController {
    @Autowired
    AuthorityService authorityService;

    /**
     * 查询所有用户权限
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/authorityAll")
    public Object queryAuthorityAll(@RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "1") int pageNum){
        log.info("分页请求"+pageNum+";"+pageSize);
        PageDomain pageDomain=new PageDomain(pageNum,pageSize,"id", SortBy.DESC);
        PageInfo<Authority> pageInfo=authorityService.queryAuthorityAll(pageDomain);
        return ResponseBean.success(pageInfo);
    }

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    @GetMapping("/viewAuthorityById/{id}")
    public Object viewAuthorityById(@PathVariable Long id){
        Authority authority=authorityService.queryAuthorityById(id);
        return ResponseBean.success(authority);
    }

    /**
     * 编辑用户权限
     * @param authority
     * @return
     */
    @PostMapping("/editAuthority")
    public Object editAuthority(@RequestBody Authority authority){
        log.info("编辑*** 权限id+名字："+authority.getId().toString()+","+authority.getAuthority().toString());
        Integer editCount=authorityService.editAuthority(authority);
        return ResponseBean.success(editCount);
    }

    /**
     * 根据id删除权限
     * @param id
     * @return
     */
    @GetMapping("/deleteAuthorityById/{id}")
    public Object deleteAuthorityById(@PathVariable Long id){
        System.out.println("删除权限的id:"+id);
        Integer deleteCount=authorityService.deleteAuthorityById(id);
        return ResponseBean.success(deleteCount);
    }

    @PostMapping("/addAuthority")
    public Object addAuthority(@RequestBody Authority authority){
        log.info("添加*** 权限名字："+authority.getAuthority().toString());
        Integer addCount=authorityService.insertAuthority(authority.getAuthority());
        return ResponseBean.success(addCount);
    }
}
