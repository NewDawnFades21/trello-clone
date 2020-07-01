package edu.zsc.todolistproject.mapper;


import edu.zsc.todolistproject.domain.Authority;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    @Select("select * from authority")
    List<Authority> queryAuthorityAll();

    @Update("update authority set authority=#{authority} where id=#{id}")
    Integer editAuthority(Long id, String authority);

    @Select("select * from authority where id=#{id}")
    Authority queryAuthorityById(Long id);

    @Delete("delete from authority where id=#{id}")
    Integer deleteAuthorityById(Long id);

    @Delete("delete from user_authority where authority_id=#{authorityId}")
    Integer deleteUserAuthorityById(Long authorityId);

    @Insert("insert into authority(authority) values(#{authority})")
    Integer insertAuthority(String authority);
}
