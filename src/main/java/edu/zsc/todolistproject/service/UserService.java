package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    User getUserById(long id);


    int insertUser(String username, String password, String email, Date registerdate);

//    User findByUsernamePassWord(String username, String password);

    User getUserByUsername(String s);

//    int checkIfValidOldPassword(User user, String oldPassword);

    int updateUserPassword(User user);

    int register(User user);

    List<String> checkUsernameIsExist(String username);

    List<User> getUserByEmail(String email);

    String getUsernameByEmail(String email);

    int changPwdByEmail(User user);
}
