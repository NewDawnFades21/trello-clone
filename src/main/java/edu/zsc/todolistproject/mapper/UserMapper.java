package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.User;
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
}
