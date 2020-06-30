package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
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

    //个人有哪些团队 select用
    @Select("select t.title,t.id from team_member m,team t WHERE user_id=#{userId} and m.team_id=t.id")
    public List<Team> findPersonalTeam(Long userId);

    @Select("select * from team_type")
    public List<TeamType> findTeamType();



}
