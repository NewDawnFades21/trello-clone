package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
@Component
public interface LoginMapper {

    @Select("select username from user where username=#{username}")
    public User checkIsExist(String username);

    @Insert("insert into user(username,password,registerdate) values(#{username},#{password},#{registerdate})")
    public void insertUser(String username, String password, Date registerdate);

    @Select("select * from user where username=#{username} and password=#{password}")
    public User findByUsernamePassWord(String username, String password);
}
