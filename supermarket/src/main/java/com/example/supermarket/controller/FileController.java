package com.example.supermarket.controller;

import com.example.supermarket.entity.File;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public Object upload(MultipartFile file, String md5){
        System.out.println(file);
        File uploadFile = fileService.upload(file,md5);
       return new ResponseResult<Integer>(200, "success", uploadFile.getId());

    }

    @GetMapping("/getByMd5")
    public ResponseResult<File> getByMd5(String md5)  {
        File file = fileService.getByMd5(md5);

        return new ResponseResult<File>(file==null?400:200, "",file);
    }

}
