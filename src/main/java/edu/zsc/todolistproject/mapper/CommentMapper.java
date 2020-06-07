package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into comment (card_id,content,create_time,user_id) values" +
            "(#{cardId},#{content},#{createTime},#{userId})")
    int addComment(Comment comment);


    @Select("select * from comment where card_id=#{cardId}")
    List<Comment> getCommentsByCardId(Long cardId);
}
