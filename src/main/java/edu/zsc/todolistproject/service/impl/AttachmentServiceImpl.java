package edu.zsc.todolistproject.service.impl;

import edu.zsc.todolistproject.domain.Attachment;
import edu.zsc.todolistproject.mapper.AttachmentMapper;
import edu.zsc.todolistproject.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@CacheConfig(cacheNames = "attachments")
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Override
    public int addAttachment(Attachment attachment) {
        return attachmentMapper.addAttachment(attachment);
    }

//    @Cacheable(key = "'attachment_'.concat(#result.id)",unless = "#result==null")
    @Override
    public List<Attachment> getAttachmentsByCardId(Long cardId) {
        return attachmentMapper.getAttachmentsByCardId(cardId);
    }

//    @CacheEvict(key = "'attachment_'.concat(#id)",condition = "#id!=null")
    @Override
    public int deleteById(Long id) {
        return attachmentMapper.deleteById(id);
    }

//    @Cacheable(key = "'attachment_'.concat(#id)",unless = "#result==null")
    @Override
    public Attachment getAttachmentsById(Long id) {
        return attachmentMapper.getAttachmentsById(id);
    }

//    @CachePut(key = "'attachment_'.concat(#attachment.id)",unless = "#result==null")
    @Override
    public int updateAttachment(Attachment attachment) {
        return attachmentMapper.updateAttachment(attachment);
    }
}
