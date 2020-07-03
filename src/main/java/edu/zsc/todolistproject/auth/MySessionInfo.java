package edu.zsc.todolistproject.auth;

import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionInfo {
    private User user;

    @Autowired
    private UserMapper userMapper;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userMapper.getUserByUsername(username);
    }
}
