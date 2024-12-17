package com.ex.smt.Service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String FileUpload(MultipartFile file, String path);
    InputStream getResource(String name , String path);
}
