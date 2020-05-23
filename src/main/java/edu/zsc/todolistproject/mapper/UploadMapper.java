package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Attachment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper {

    @Insert("insert into attachment (filename,path,user_id,card_id) values(#{filename},#{path},#{userId},#{cardId})")
    int addAttachment(Attachment attachment);
}
