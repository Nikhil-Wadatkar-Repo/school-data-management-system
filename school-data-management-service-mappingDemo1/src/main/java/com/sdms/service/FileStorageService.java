package com.sdms.service;

import com.sdms.entity.DBFile;
import com.sdms.exception.FileNotFoundException;
//import jakarta.annotation.Resource;
import com.sdms.repo.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    @Autowired
    private DBFileRepository fileRepository;

    public DBFile storeFile(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] data = file.getBytes();

            DBFile dbFile = new DBFile(filename, contentType, data);
            return fileRepository.save(dbFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }
    }

    public DBFile getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }
}
