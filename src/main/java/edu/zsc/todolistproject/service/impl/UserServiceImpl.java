package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Cacheable(key = "'user_'.concat(#id)",unless = "#result==null")
    @Override
    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int insertUser(String username, String password, String email, Date registerdate) {
        return 0;
    }

//    @Override
//    public User findByUsernamePassWord(String username, String password) {
//        return userMapper.get;
//    }
    @Cacheable(key = "'user_'.concat(#result.id)",unless = "#result==null")
    @Override
    public User getUserByUsername(String s) {
        return userMapper.getUserByUsername(s);
    }

//    @Override
//    public int checkIfValidOldPassword(User user, String oldPassword) {
//        return 0;
//    }

    @CachePut(key = "'user_'.concat(#user.id)")
    @Override
    public int updateUserPassword(User user) {
        return userMapper.updateUserPassword(user);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Cacheable(key = "'user_'.concat(#username)",unless = "#result==null")
    @Override
    public List<String> checkUsernameIsExist(String username) {
        return userMapper.checkUsernameIsExist(username);
    }

    @Cacheable(key = "'user_'.concat(#email)",unless = "#result==null")
    @Override
    public List<User> getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Cacheable(key = "'user_'.concat(#email)",unless = "#result==null")
    @Override
    public String getUsernameByEmail(String email) {
        return userMapper.getUsernameByEmail(email);
    }

    @CachePut(key = "'user_'.concat(#user.id)")
    @Override
    public int changPwdByEmail(User user) {
        return userMapper.changPwdByEmail(user);
    }
}
