package edu.zsc.todolistproject.service;

import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;

public interface AuthorityService {

    /**
     * 查询所有权限
     * @param pageDomain
     * @return
     */
    public PageInfo<Authority> queryAuthorityAll(PageDomain pageDomain);

    /**
     * 修改权限名
     * @param authority
     * @return
     */
    public Integer editAuthority(Authority authority);

    /**
     * 查询权限，根据权限id
     * @param id
     * @return
     */
    public Authority queryAuthorityById(Long id);

    /**
     * 删除权限，根据权限id
     * @param id
     * @return
     */
    public Integer deleteAuthorityById(Long id);

    /**
     * 添加权限，添加权限名（id自动生成）
     * @param authority
     * @return
     */
    public Integer insertAuthority(String authority);

}
