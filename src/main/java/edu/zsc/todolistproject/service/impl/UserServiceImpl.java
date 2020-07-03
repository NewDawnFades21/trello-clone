package edu.zsc.todolistproject.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.UserAuthority;
import edu.zsc.todolistproject.mapper.UserMapper;
import edu.zsc.todolistproject.service.UserService;
import edu.zsc.todolistproject.vo.UserAuthorityDetail;
import edu.zsc.todolistproject.vo.requestVo.QueryUser;
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

//@Transactional
@Service
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

//    @Cacheable(key = "'user_'.concat(#id)",unless = "#result==null")
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
//    @Cacheable(key = "'user_'.concat(#result.id)",unless = "#result==null")
    @Override
    public User getUserByUsername(String s) {
        return userMapper.getUserByUsername(s);
    }

//    @Override
//    public int checkIfValidOldPassword(User user, String oldPassword) {
//        return 0;
//    }

//    @CachePut(key = "'user_'.concat(#user.id)")
    @Override
    public int updateUserPassword(User user) {
        return userMapper.updateUserPassword(user);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

//    @Cacheable(key = "'user_'.concat(#username)",unless = "#result==null")
    @Override
    public List<String> checkUsernameIsExist(String username) {
        return userMapper.checkUsernameIsExist(username);
    }

//    @Cacheable(key = "'user_'.concat(#email)",unless = "#result==null")
    @Override
    public List<User> getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

//    @Cacheable(key = "'user_'.concat(#email)",unless = "#result==null")
    @Override
    public String getUsernameByEmail(String email) {
        return userMapper.getUsernameByEmail(email);
    }

//    @CachePut(key = "'user_'.concat(#user.id)")
    @Override
    public int changPwdByEmail(User user) {
        return userMapper.changPwdByEmail(user);
    }

    //后台
    @Override
    public PageInfo<UserAuthorityDetail> queryUserAll(PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderStr());
        return new PageInfo<>(userMapper.queryUserAuthoryAll());
    }

    @Override
    public UserAuthorityDetail queryUserById(Long userId) {
        return userMapper.queryUserAuthorityByUserId(userId);
    }

    @Override
    public Integer deleteUserById(Long userId) {
        Integer deleteCount=0;
        //删除关联表数据
        deleteCount+=userMapper.deleteAttachmentByUserId(userId);
        deleteCount+=userMapper.deleteBoardByUserId(userId);
        deleteCount+=userMapper.deleteChecklistByUserId(userId);
        deleteCount+=userMapper.deleteCommentByUserId(userId);
        deleteCount+=userMapper.deleteTeammemberByUserId(userId);
        deleteCount+=userMapper.deleteUserAuthorityByUserId(userId);
        //删除主表数据
        deleteCount+=userMapper.deleteUserById(userId);
        System.out.println(deleteCount+"删除数量");

        return deleteCount;
    }

    @Override
    public Integer insertUser(UserAuthorityDetail userAuthorityDetail) {
        java.util.Date dd=new java.util.Date();
        java.sql.Date date=new java.sql.Date(dd.getTime());
        userAuthorityDetail.setRegisterdate(date);
        User user=userAuthorityDetail.getUser();
        Integer userCount=0;
        //userCount= userMapper.insertUser(user);

        Long userId=user.getId();
        Long authorityId=userAuthorityDetail.getAuthorityId();
        Integer authorityCount=0;
        if(userAuthorityDetail.getAuthorityId()!=null){
            System.out.println("用户id，权限id:"+userId+","+authorityId);
            authorityCount=userMapper.insertUserAuthority(userId,authorityId);
        }
        System.out.println(userCount+authorityCount+"添加数量");
        return userCount+authorityCount;
    }

    @Override
    public Integer updateUser(UserAuthorityDetail userAuthorityDetail) {
//        java.util.Date dd=new java.util.Date();
//        Date date=new Date(dd.getTime());
//        userAuthorityDetail.setRegisterdate(date);
        User user=userAuthorityDetail.getUser();
        Integer userCount=userMapper.updateUserById(user);

        Long userId=userAuthorityDetail.getUserId();
        Integer authorityCount=0;
        String authorityIds="";
        UserAuthority queryUserAuthority=null;
        List<Authority> authorityList=userAuthorityDetail.getAuthorityList();
        for(Authority authority:authorityList){
            authorityIds+=authority.getAuthority()+",";
            queryUserAuthority= userMapper.queryUserAuthorityByIds(userId,authority.getId());
            if(queryUserAuthority.getId()!=authority.getId()){
                authorityCount+=userMapper.insertUserAuthority(userId,authority.getId());
            }
        }
        authorityIds="( "+authorityIds.substring(0,authorityIds.length()-1)+" )";
        Integer deleteCount= userMapper.deleteUserAuthorityByAuthorityIds(userId,authorityIds);
        return userCount+authorityCount;
    }

    @Override
    public Integer updateUser(User user) {
//        java.util.Date d = new java.util.Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dd=sdf.format(d);
//        Date date=new Date(d.getTime());
//        System.out.println(dd+"时间"+date+"*"+new java.util.Date());

        // user.setRegisterDate(new java.util.Date());
        System.out.println(user+"编辑用户");
        Integer userCount=userMapper.updateUserById(user);
        return userCount;
    }

    @Override
    public PageInfo<UserAuthorityDetail> queryUserByUsernameAuthrityEmailDate(PageDomain pageDomain,String userName,String email,Long authorityId) {
        PageHelper.startPage(pageDomain.getPageNum(),pageDomain.getPageSize(),pageDomain.getOrderStr());
        QueryUser queryUser=new QueryUser();
        queryUser.setUsername(userName);
        queryUser.setEmail(email);
        queryUser.setAuthorityId(authorityId);
        return new PageInfo<>(userMapper.queryUserAuthorityByusernameEmailAuthorityDate(queryUser));
    }

    @Override
    public List<Authority> viewAuthorityExcludeUserByuserId(Long userId) {
        return userMapper.viewAuthorityExcludeUserByuserId(userId);
    }

    @Override
    public List<Authority> viewAuthorityContainUserByuserId(Long userId) {
        return userMapper.queryauthorityByuserId(userId);
    }

    @Override
    public Integer addUserAuthority(UserAuthority userAuthority) {
        return userMapper.insertUserAuthority(userAuthority.getUserId(),userAuthority.getAuthorityId());
    }

    @Override
    public Integer deleteUserAuthority(UserAuthority userAuthority) {
        System.out.println(userAuthority+"删除用户权限");
        return userMapper.deleteUserAuthorityByIds(userAuthority.getUserId(),userAuthority.getAuthorityId());
    }

    @Override
    public Integer activateUsers(List<Long> userIds) {
        Integer activateCount=0;
        for(Long userId:userIds){
            activateCount+=userMapper.activateUserByUserId(userId);
        }
        return activateCount;
    }

    @Override
    public Integer stopUsers(List<Long> userIds) {
        Integer stopCount=0;
        for(Long userId:userIds){
            stopCount+=userMapper.stopUserByUserId(userId);
        }
        return stopCount;
    }

    @Override
    public Integer deleteByIds(List<Long> userIds) {
        Integer deleteCount=0;
        for(Long userId:userIds){
            this.deleteUserById(userId);
            deleteCount++;
        }
        return deleteCount;
    }

    @Override
    public Integer resetPasswords(List<Long> userIds) {
        return userIds.size();
    }
    //后台**
}
