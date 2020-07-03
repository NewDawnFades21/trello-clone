package edu.zsc.todolistproject.mapper;

import edu.zsc.todolistproject.TodoListProjectApplication;
import edu.zsc.todolistproject.domain.ToDoItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ToDoMapper {
    @Select("select * from todoitem where id=#{id}")
    ToDoItem getToDoItemById(Long id);

    @Delete("delete from todoitem where id=#{id}")
    int deleteToDoItemById(Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into todoitem(description,is_done,checklist_id) values(#{description},#{isDone},#{checklistId})")
    int insertToDoItem(ToDoItem toDoItem);

    @Update("update todoitem set description=#{description},is_done=#{isDone} where id=#{id}")
    int updateToDoItem(ToDoItem toDoItem);

    @Select("select * from todoitem where checklist_id = #{checklistId}")
    List<ToDoItem> getToDoItemsByChecklistId(Long checklistId);

    @Delete("delete from todoitem where checklist_id = #{id}")
    int deleteToDoItemByChecklistId(Long id);
}
