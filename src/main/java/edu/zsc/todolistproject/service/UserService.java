package edu.zsc.todolistproject.service;

import com.github.pagehelper.PageInfo;
import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.PageDomain;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.UserAuthority;
import edu.zsc.todolistproject.vo.UserAuthorityDetail;

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

    //后台
    /**
     * 查询用户，全部
     * @param pageDomain
     * @return
     */
    public PageInfo<UserAuthorityDetail> queryUserAll(PageDomain pageDomain);

    /**
     * 查询用户，根据用户id
     * @param userId
     * @return
     */
    public UserAuthorityDetail queryUserById(Long userId);

    /**
     * 删除用户，根据用户id
     * @param userId
     * @return
     */
    public Integer deleteUserById(Long userId);

    /**
     * 添加用户
     * @param userAuthorityDetail
     * @return
     */
    public Integer insertUser(UserAuthorityDetail userAuthorityDetail);

    /**
     * 修改用户
     * @param userAuthorityDetail
     * @return
     */
    public Integer updateUser(UserAuthorityDetail userAuthorityDetail);
    public Integer updateUser(User user);

    /**
     * 查询用户，根据用户名，邮箱，权限id,创建时间段
     * @param pageDomain
     * @return
     */
    public PageInfo<UserAuthorityDetail> queryUserByUsernameAuthrityEmailDate(PageDomain pageDomain,String userName,String email,Long authority);

    /**
     * 查询权限，根据用户id查询该用户不包含的权限
     * @param userId
     * @return
     */
    public List<Authority> viewAuthorityExcludeUserByuserId(Long userId);

    /**
     * 查询权限，根据用户id查询该用户权限
     * @param userId
     * @return
     */
    public List<Authority> viewAuthorityContainUserByuserId(Long userId);

    /**
     * 添加用户权限
     * @param userAuthority
     * @return
     */
    public Integer addUserAuthority(UserAuthority userAuthority);

    /**
     * 删除用户权限
     * @param userAuthority
     * @return
     */
    public Integer deleteUserAuthority(UserAuthority userAuthority);

    /**
     * 批量激活用户
     * @param userIds
     * @return
     */
    public Integer activateUsers(List<Long> userIds);

    /**
     * 批量停用用户
     * @param userIds
     * @return
     */
    public Integer stopUsers(List<Long> userIds);

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    public Integer deleteByIds(List<Long> userIds);

    /**
     * 批量重置密码
     * @param userIds
     * @return
     */
    public Integer resetPasswords(List<Long> userIds);

    //后台管理**
}
