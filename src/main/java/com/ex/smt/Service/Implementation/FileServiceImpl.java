package com.ex.smt.Service.Implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ex.smt.Exception.BadApiRequest;
import com.ex.smt.Service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String FileUpload(MultipartFile file, String path) {
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename : {}", originalFilename);

        // Generate a unique file name
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;

        // Trim any trailing slashes from the path
        path = path.trim();
        if (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1); // Remove the trailing separator
        }

        String fullFileName = path + File.separator + fileNameWithExtension;

        // Check file extension for allowed image types
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            // Ensure directory exists
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Upload file
            try {
                Files.copy(file.getInputStream(), Paths.get(fullFileName));
            } catch (IOException e) {
                logger.error("Error saving file: {}", e.getMessage());
                throw new RuntimeException("Failed to upload file", e);
            }
            return fullFileName;
        } else {
            throw new BadApiRequest("File with this " + extension + " not allowed");
        }
    }

    @Override
    public InputStream getResource(String path, String name) {
        path = path.trim();
        if (path.endsWith(File.separator)) {
            path = path.substring(0, path.length() - 1); 
        }

        String fullPath = path + File.separator + name;
        InputStream inputStream = null;

        try {
            File file = new File(fullPath);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                logger.error("File not found: {}", fullPath);
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", fullPath);
            e.printStackTrace();
        }

        return inputStream;
    }
}
