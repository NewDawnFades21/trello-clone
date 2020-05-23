package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from board where id=#{id}")
    Board getBoardById(Long id);

    @Delete("delete from board where id=#{id}")
    int deleteBoardById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into Board(title,visibility) values(#{title},#{visibility})")
    int insertBoard(Board board);

    @Update("update board set title=#{title},visibility=#{visibility} where id=#{id}")
    int updateBoard(Board board);

    @Select("select * from board")
    List<Board> getBoards();
}
