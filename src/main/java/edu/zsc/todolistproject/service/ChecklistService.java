package edu.zsc.todolistproject.service;

import edu.zsc.todolistproject.domain.Checklist;

import java.util.List;

public interface ChecklistService {
    int addChecklist(Checklist checklist);

    List<Checklist> getChecklistsByCardId(Long cardId);

    Checklist getChecklistById(Long id);

    int updateChecklistPercent(int percent, Long id);

    int deleteChecklistById(Long id);
}
