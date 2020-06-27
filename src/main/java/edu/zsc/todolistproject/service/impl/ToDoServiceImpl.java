package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.ToDoItem;
import edu.zsc.todolistproject.mapper.ToDoMapper;
import edu.zsc.todolistproject.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Transactional
//@CacheConfig(cacheNames = "items")
@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoMapper toDoMapper;

//    @Cacheable(key = "'item_'.concat(#id)")
    @Override
    public ToDoItem getToDoItemById(Long id) {
        return toDoMapper.getToDoItemById(id);
    }

//    @CacheEvict(key = "'item_'.concat(#id)")
    @Override
    public int deleteToDoItemById(Long id) {
        return toDoMapper.deleteToDoItemById(id);
    }

    @Override
    public int insertToDoItem(ToDoItem toDoItem) {
        return toDoMapper.insertToDoItem(toDoItem);
    }

//    @CachePut(key = "'item_'.concat(#toDoItem.id)")
    @Override
    public int updateToDoItem(ToDoItem toDoItem) {
        return toDoMapper.updateToDoItem(toDoItem);
    }

//    @Cacheable(unless = "#result==null")
    @Override
    public List<ToDoItem> getToDoItemsByChecklistId(Long checklistId) {
        return toDoMapper.getToDoItemsByChecklistId(checklistId);
    }
}
