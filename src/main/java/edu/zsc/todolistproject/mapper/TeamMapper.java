package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.vo.TeamDetail;
import org.apache.ibatis.annotations.*;
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

//后台管理添加的
    @Select("select * from team_type")
    public List<TeamType> queryTeamTypeAll();

    public List<TeamDetail> queryTeamDetailByMap(String username, String title, Long typeId);

    public TeamDetail queryTeamDetailByTeamId(Long teamId);

    @Select("select * from team_type where id=#{teamTypeId}")
    public TeamType queryTeamTypeById(Long teamTypeId);

    @Select("select u.* from team_member as tm , user as u where u.id=tm.user_id and tm.team_id=#{teamId}")
    List<User> queryUserByteamId(Long teamId);

    @Insert("insert into team_type(name) values(#{teamType})")
    public Integer addTeamType(String teamType);

    @Insert("insert into team(type_id,title,description) values(#{typeId},#{title},#{description})")
    public Integer addTeam(TeamDetail teamDetail);

    @Update("update team_type set name=#{name} where id=#{id}")
    public Integer editTeamType(TeamType teamType);

    @Update("update team set type_id=#{typeId},description=#{description},title=#{title} where id=#{id}")
    Integer editTeam(TeamDetail teamDetail);

    @Delete("delete from team_type where id=#{teamTypeId}")
    public Integer deleteTeamTypeByTeamTypeId(Long teamTypeId);

    @Delete("delete from team where type_id=#{teamTypeId}")
    public Integer deleteTeamByTeamTypeId(Long teamTypeId);

    @Delete("delete from team_member where team_id in(select id from team where type_id=#{teamTypeId})")
    public Integer deleteTeamMemberByTeamTypeId(Long teamTypeId);

    @Delete("delete from team_member where team_id=#{teamId}")
    Integer deleteTeamMemberByTeamId(Long teamId);

    @Delete("delete from team where id=#{teamId}")
    Integer deleteTeamByTeamId(Long teamId);

}
