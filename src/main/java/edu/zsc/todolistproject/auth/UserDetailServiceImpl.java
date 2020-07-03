package edu.zsc.todolistproject.auth;

import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userMapper.getUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
