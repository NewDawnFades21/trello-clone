package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Mapper
@Component
public interface BoardMapper2 {

    @Select("select * from board where id=#{id}")
    public Board findBoardById(Long id);

    @Insert("insert into board(title,visibility,user_id,submit_date,team_id) values (#{title},#{visibility},#{userId},#{submitDate},#{teamId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertBoard(Board board);

    @Insert("insert into board(title,visibility,user_id,submit_date) values (#{title},#{visibility},#{userId},#{submitDate})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertBoardNoTeam(Board board);


    @Select("select * from board where ISNULL(team_id)=1 and user_id=#{userId}")
    public List<Board> findAllBoard(Long userId);

    @Select("select * from board where user_id=#{userId} order by submit_date desc limit 0,4")
    public List<Board> findBoardSubmitDate(Long userId);

    //改
    //个人团队面板
    @Select("select * from board where user_id=#{userId} and team_id=#{teamId}")
    public List<Board> findPeraonalTeamBoard(Long userId, Long teamId);

    //所有团队面板
    @Select("select * from board where user_id != #{userId} and team_id=#{teamId}")
    public List<Board> findAllTeamBoard(Long userId, Long teamId);




    @Insert("insert into team_member(team_id,user_id) values(#{teamId},#{userId})")
    public void insertTeamMember(Long teamId,Long userId);

    @Insert("insert into team(type_id,description,title) values(#{typeId},#{description},#{title})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insertTeam(Team team);

    @Select("select id from user where username=#{username}")
    public Long findUserId(String username);

    public void insertB(Board board);


//    public Team get(Long id);


}
