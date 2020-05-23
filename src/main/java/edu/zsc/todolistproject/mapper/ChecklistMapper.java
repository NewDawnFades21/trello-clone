package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.domain.Checklist;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChecklistMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into checklist (card_id,title,user_id,percent) values(#{cardId},#{title},#{userId},#{percent})")
    int addChecklist(Checklist checklist);

    @Select("select * from checklist where card_id = #{cardId}")
    List<Checklist> getChecklistsByCardId(Long cardId);

    @Select("select * from checklist where id = ${id}")
    Checklist getChecklistById(Long id);

    @Update("update checklist set percent = #{percent} where id = ${id}")
    int updateChecklistPercent(int percent, Long id);
}
