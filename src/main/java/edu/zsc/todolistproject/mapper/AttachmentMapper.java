package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Attachment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttachmentMapper {
    @Insert("insert into attachment (filename,path,user_id,card_id,create_time) values(#{filename},#{path},#{userId},#{cardId},#{createTime})")
    int addAttachment(Attachment attachment);

    @Select("select * from attachment where card_id = #{cardId}")
    List<Attachment> getAttachmentsByCardId(Long cardId);
}
