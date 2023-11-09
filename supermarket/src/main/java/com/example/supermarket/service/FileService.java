package com.example.supermarket.service;

import com.example.supermarket.entity.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File upload(MultipartFile file, String md5);

    File getByMd5(String md5);

}
