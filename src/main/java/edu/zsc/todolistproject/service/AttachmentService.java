package edu.zsc.todolistproject.service;


import edu.zsc.todolistproject.domain.Attachment;
import java.util.List;

public interface AttachmentService {
    int addAttachment(Attachment attachment);

    List<Attachment> getAttachmentsByCardId(Long cardId);

    int deleteById(Long id);

    Attachment getAttachmentsById(Long id);

    int updateAttachment(Attachment attachment);
}
