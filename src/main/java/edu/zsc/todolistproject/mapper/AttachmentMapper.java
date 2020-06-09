package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Attachment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttachmentMapper {
    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into attachment (filename,path,user_id,card_id,create_time) values(#{filename},#{path},#{userId},#{cardId},#{createTime})")
    int addAttachment(Attachment attachment);

    @Select("select * from attachment where card_id = #{cardId}")
    List<Attachment> getAttachmentsByCardId(Long cardId);

    @Delete("delete from attachment where id = #{id}")
    int deleteById(Long id);

    @Select("select * from attachment where id = #{id}")
    Attachment getAttachmentsById(Long id);

    @Update("update attachment set filename =#{filename},path = #{path},user_id = #{userId},card_id = #{cardId},create_time = #{createTime} where id = #{id}")
    int updateAttachment(Attachment attachment);
}
