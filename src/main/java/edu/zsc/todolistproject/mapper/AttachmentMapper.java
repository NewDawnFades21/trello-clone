package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Attachment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachmentMapper {

    @Insert("insert into attachment (path,user_id,card_id) values(#{path},#{userId},#{cardId})")
    int addAttachment(Attachment attachment);
}
