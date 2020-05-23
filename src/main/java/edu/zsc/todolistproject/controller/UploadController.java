package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Attachment;
import edu.zsc.todolistproject.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {
    private static String UPLOAD_DIR = "C:\\uploads\\";

    @Autowired
    private AttachmentMapper attatchmentMapper;

    @PostMapping("/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(MultipartFile file, Attachment attachment) {
        try {
            String uploadFilePath = UPLOAD_DIR + file.getOriginalFilename();
            attachment.setFilename(file.getOriginalFilename());
            attachment.setPath(uploadFilePath);
            attatchmentMapper.addAttachment(attachment);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("/uploads/" + file.getOriginalFilename(), HttpStatus.OK);
    }

    @PostMapping("/downloadFile")
    public ResponseEntity<?> downloadFileToUploadPath(@RequestBody Map<String, Object> map) {
        String attach_url = (String) map.get("attach_url");
        try {
            URLConnection connection = new URL(attach_url).openConnection();
            System.setProperty("http.agent", "Chrome");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
            Files.copy(connection.getInputStream(), Paths.get(UPLOAD_DIR + attach_url), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // handle exception
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("/uploads/" + attach_url, HttpStatus.OK);
    }


}
