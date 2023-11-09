package com.example.supermarket.service.impl;

import com.example.supermarket.entity.File;
import com.example.supermarket.mapper.FileMapper;
import com.example.supermarket.service.FileService;
import com.example.supermarket.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

        @Autowired
        MyUtil myUtil;
        @Autowired
        FileMapper fileMapper;
        @Override
        public File upload(MultipartFile file, String md5) {
            //存到服务器上的某个位置
            String path = myUtil.upload(file);
            File f = new File();
            f.setPath(path);
            f.setMd5(md5);
            //把这个路径存到数据中
            fileMapper.add(f);
            return f;
        }

    @Override
    public File getByMd5(String md5) {
         return fileMapper.getByMd5(md5);
    }

}
