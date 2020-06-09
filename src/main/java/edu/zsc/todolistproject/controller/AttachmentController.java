package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Attachment;
import edu.zsc.todolistproject.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("id") Long id){
        int result = attachmentMapper.deleteById(id);
        if (result == 1){
            return new ResponseEntity<>("删除附件成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("删除附件失败", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttachment(Long id,String filename){
        Attachment attachment = attachmentMapper.getAttachmentsById(id);
        attachment.setFilename(filename);
        int res = attachmentMapper.updateAttachment(attachment);
        if (res == 1)
            return new ResponseEntity<String>("修改成功",HttpStatus.OK);
        else
            return new ResponseEntity<String>("修改失败",HttpStatus.SERVICE_UNAVAILABLE);
    }
}
