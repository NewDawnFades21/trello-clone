package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Checklist;
import edu.zsc.todolistproject.mapper.ChecklistMapper;
import edu.zsc.todolistproject.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@CacheConfig(cacheNames = "checklists")
public class ChecklistServiceImpl implements ChecklistService {

    @Autowired
    private ChecklistMapper checklistMapper;
    @Override
    public int addChecklist(Checklist checklist) {
        return checklistMapper.addChecklist(checklist);
    }

//    @Cacheable(key = "'checlists_card'.concat(#cardId)",unless = "#result==null")
    @Override
    public List<Checklist> getChecklistsByCardId(Long cardId) {
        return checklistMapper.getChecklistsByCardId(cardId);
    }

//    @Cacheable(key = "'checklist_'.concat(#id)",unless = "#result==null")
    @Override
    public Checklist getChecklistById(Long id) {
        return checklistMapper.getChecklistById(id);
    }

//    @CachePut(key = "'checklist_'.concat(#id)",unless = "#result==null")
    @Override
    public int updateChecklistPercent(int percent, Long id) {
        return checklistMapper.updateChecklistPercent(percent, id);
    }
}
