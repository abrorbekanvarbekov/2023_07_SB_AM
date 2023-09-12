package com.example.demo.UsrArticleController;

import com.example.demo.service.FileService;
import com.example.demo.util.Util;
import com.example.demo.vo.FileVo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;


@Controller
public class FileUploadController {

    private FileService fileService;
    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping("/usr/home/fileUpload")
    @ResponseBody
    public String fileUpload(MultipartFile file){

        if (file.getSize() == 0){
            return Util.jsHistoryBack("file upload 해주세요");
        }

        try {
            fileService.saveFile(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Util.jsReplace("파일 업로드 성공", "/");
    }


    @RequestMapping("usr/home/view")
    public String view( Model model){
        List<FileVo> files = fileService.getFiles();
        model.addAttribute(files);
        return "usr/home/view";
    }

    @RequestMapping("usr/home/file/{fileId}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("fileId") int id, Model model) throws MalformedURLException {
        FileVo fileVo = fileService.getFileById(id);
        return new UrlResource("file:" + fileVo.getSavePath());
    }
}


