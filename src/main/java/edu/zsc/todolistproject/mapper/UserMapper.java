package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Authority;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.domain.UserAuthority;
import edu.zsc.todolistproject.vo.UserAuthorityDetail;
import edu.zsc.todolistproject.vo.requestVo.QueryUser;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User getUserById(long id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(username,password,email,registerdate) values(#{username},#{password},#{email},#{registerdate})")
    int insertUser(String username, String password, String email, Date registerdate);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernamePassWord(String username, String password);


    User getUserByUsername(String s);

//    int checkIfValidOldPassword(User user, String oldPassword);

    @Update("update user set password = #{password} where id = #{id}")
    int updateUserPassword(User user);

    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into user (username,password,registerdate,email,introduction,authorities) values (#{username},#{password},#{registerDate},#{email},#{introduction},'ROLE_USER')")
    int register(User user);

    @Select("select username from user where username = #{username}")
    List<String> checkUsernameIsExist(String username);

    @Select("select * from user where email = #{email}")
    List<User> getUserByEmail(String email);

    @Select("select username from user where email = #{email}")
    String getUsernameByEmail(String email);

    @Update("update user set password = #{password} where email = #{email}")
    int changPwdByEmail(User user);

    //后台管理新添加

    /**
     * 查询所有单表用户
     * @return
     */
    @Select("select * from user")
    List<User> queryUserAll();

    /**
     * 查询权限列表，根据用户id联表查询
     * @param userId
     * @return
     */
    @Select("select a.* from user_authority as ua , authority as a where a.id=ua.authority_id and ua.user_id=#{userId}")
    List<Authority> queryauthorityByuserId(Long userId);

    /**
     * 查询用户，根据用户名，邮箱，权限id,创建时间段联表查询
     * @param queryUser
     * @return
     */
    List<UserAuthorityDetail> queryUserAuthorityByusernameEmailAuthorityDate(QueryUser queryUser);

    /**
     *  查询用户权限表,根据用户id和权限id，单表查询
     * @param userId
     * @param authorityId
     * @return
     */
    @Select("select * from user_authority wehre user_id=#{userId} and authority_id=#{authorityId}")
    public UserAuthority queryUserAuthorityByIds(Long userId, Long authorityId);
    /**
     * 查询所有用户，联表查询
     * @return
     */
    List<UserAuthorityDetail> queryUserAuthoryAll();

    /**
     * 查询单个用户，根据用户id联表查询
     * @param userId
     * @return
     */
    UserAuthorityDetail queryUserAuthorityByUserId(Long userId);

    /**
     * 查询权限，根据用户id查询不包含该用户已有权限
     * @param userId
     * @return
     */
    @Select("select * from authority  where id not in " +
            "(select authority_id from user_authority where user_id=#{userId})")
    List<Authority> viewAuthorityExcludeUserByuserId(Long userId);

    //插入********************************************************************************

    /**
     * 添加用户信息，单表插入
     * @param user
     * @return
     */
//    @Insert("insert into user(username,password,email,introduction,registerDate,status)" +
//            "values(#{username},#{password},#{email},#{introduction},#{registerDate},#{status})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")//返回自增主键，保存在id
//    public Integer insertUser(User user);

    /**
     * 添加用户权限，插入用户id和权限id
     * @param userId
     * @param authorityId
     * @return
     */
    @Insert("insert into user_authority(user_id,authority_id)" +
            "values(#{userId},#{authorityId})")
    public Integer insertUserAuthority(Long userId,Long authorityId);

    //修改********************************************************************************

    /**
     * 修改用户信息，单表操作
     * @param user
     * @return
     */
    @Update("update user set username=#{username},password=#{password},email=#{email}," +
            "introduction=#{introduction} ,status=#{status} where id=#{id}") //,registerdate=#{registerDate}
    public Integer updateUserById(User user);

    @Update("update user set status=1 where id=#{userId}")
    public Integer activateUserByUserId(Long userId);

    @Update("update user set status=0 where id=#{userId}")
    public Integer stopUserByUserId(Long userId);



    //删除********************************************************************************

    /**
     * 删除用户权限
     * @param userId
     * @param authorityIds
     * @return
     */
    @Delete("delete from user_authority where user_id=#{userId} and authority_id not in #{authorityIds}")
    public Integer deleteUserAuthorityByAuthorityIds(Long userId,String authorityIds);

    /**
     * 删除用户权限，根据用户id和权限id，单表删除
     * @param userId
     * @param authorityId
     * @return
     */
    @Delete("delete from user_authority where user_id=#{userId} and authority_id=#{authorityId}")
    public Integer deleteUserAuthorityByIds(Long userId,Long authorityId);

    /**
     * 删除用户，根据用户id，删除单表用户数据
     * @param userId
     * @return
     */
    @Delete("delete from user where id=#{userId}")
    public Integer deleteUserById(Long userId);

    @Delete("delete from attachment where user_id=#{userId}")
    public Integer deleteAttachmentByUserId(Long userId);

    @Delete("delete from board where user_id=#{userId}")
    public Integer deleteBoardByUserId(Long userId);

    @Delete("delete from checklist where user_id=#{userId}")
    public Integer deleteChecklistByUserId(Long userId);

    @Delete("delete from comment where user_id=#{userId}")
    public Integer deleteCommentByUserId(Long userId);

    @Delete("delete from team_member where user_id=#{userId}")
    public Integer deleteTeammemberByUserId(Long userId);

    @Delete("delete from user_authority where user_id=#{userId}")
    public Integer deleteUserAuthorityByUserId(Long userId);






}
