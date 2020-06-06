package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeamMapper {



    //改
    @Insert("insert into team(title,type_id,description) values (#{title},#{type_id},#{description})")
    public void insertTeam(String title, Long typeId, String description);


   @Select("select  t.*  from team_member as m,team as t where m.user_id=#{userId} and t.id=m.team_id")
    public List<Team> findUserTeam(Long userId);



}
