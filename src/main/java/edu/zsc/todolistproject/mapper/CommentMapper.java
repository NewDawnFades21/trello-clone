package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into comment (card_id,content,create_time,user_id) values" +
            "(#{cardId},#{content},#{createTime},#{userId})")
    int addComment(Comment comment);


    @Select("select * from comment where card_id=#{cardId}")
    List<Comment> getCommentsByCardId(Long cardId);

    @Select("select * from comment where id = #{id}")
    Comment getCommentById(Long id);

    @Update("update comment set content = #{content},modified = #{modified} where id = #{id}")
    int editComment(Comment comment);

    @Delete("delete from comment where id = #{id}")
    int deleteComment(Long id);
}
