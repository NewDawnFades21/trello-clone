package edu.zsc.todolistproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.mapper.AuthorityMapper;
import edu.zsc.todolistproject.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public PageInfo<Authority> queryAuthorityAll(PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderStr());
        return new PageInfo<>(authorityMapper.queryAuthorityAll());
    }

    @Override
    public Integer editAuthority(Authority authority) {
        return authorityMapper.editAuthority(authority.getId(),authority.getAuthority());
    }

    @Override
    public Authority queryAuthorityById(Long id) {
        return authorityMapper.queryAuthorityById(id);
    }

    @Override
    public Integer deleteAuthorityById(Long id) {
        Integer deleteCount=0;
        deleteCount+=authorityMapper.deleteUserAuthorityById(id);
        deleteCount=authorityMapper.deleteAuthorityById(id);
        return deleteCount;
    }

    @Override
    public Integer insertAuthority(String authority) {
        return authorityMapper.insertAuthority(authority);
    }
}
