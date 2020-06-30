package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Attachment;
import edu.zsc.todolistproject.mapper.AttachmentMapper;
import edu.zsc.todolistproject.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    private static String UPLOAD_DIR = "C:\\uploads\\";
    @Autowired
    private AttachmentService attachmentService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("id") Long id){
        int result = attachmentService.deleteById(id);
        if (result == 1){
            return new ResponseEntity<>("删除附件成功", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("删除附件失败", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/update/{id}/{filename}")
    public ResponseEntity<?> updateAttachment(@PathVariable("id") Long id,@PathVariable("filename") String filename) throws IOException {
        Attachment attachment = attachmentService.getAttachmentsById(id);
//        update local file's filename
        // File (or directory) with old name
        File file = new File(attachment.getPath());

// File (or directory) with new name
        File file2 = new File(UPLOAD_DIR+filename);

        if (file2.exists())
            throw new java.io.IOException("file exists");

// Rename file (or directory)
        boolean success = file.renameTo(file2);

        if (!success) {
            // File was not successfully renamed
            return new ResponseEntity<String>("同步失败",HttpStatus.SERVICE_UNAVAILABLE);
        }
        attachment.setFilename(filename);
        attachment.setPath(UPLOAD_DIR+filename);
        int res = attachmentService.updateAttachment(attachment);
        if (res == 1)
            return new ResponseEntity<Attachment>(attachment,HttpStatus.OK);
        else
            return new ResponseEntity<String>("更新失败",HttpStatus.SERVICE_UNAVAILABLE);
    }
}
