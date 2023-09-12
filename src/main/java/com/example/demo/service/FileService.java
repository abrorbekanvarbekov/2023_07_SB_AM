package com.example.demo.service;

import com.example.demo.dao.FileDoa;
import com.example.demo.vo.FileVo;
import com.example.demo.vo.ResultDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    private FileDoa fileDoa;

    @Value("${file.dir}")
    private String fileDir;

    public FileService(FileDoa fileDoa) {
        this.fileDoa = fileDoa;
    }

    public void saveFile(MultipartFile file) throws IOException {

        if (file.isEmpty()){
            return;
        }

        String orgName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = orgName.substring(orgName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDir + "/" + savedName;
        fileDoa.insertFileInfo(orgName, savedName, savedPath);
        file.transferTo(new File(savedPath));
    }

    public List<FileVo> getFiles() {
        return fileDoa.getFiles();
    }

    public FileVo getFileById(int id) {
        return fileDoa.getFileById(id);
    }
}

