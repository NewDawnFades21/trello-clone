package edu.zsc.todolistproject.vo;

import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.User;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class UserAuthorityDetail {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String introduction;
    private Date registerdate;
    private int status;
    List<Authority> authorityList;
    private Long authorityId;

    public User getUser(){
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setIntroduction(introduction);
        user.setRegisterDate(registerdate);
        user.setStatus(status);
        return user;
    }
}
