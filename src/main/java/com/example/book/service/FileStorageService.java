package com.example.book.service;

import com.example.book.exception.FileStorageException;
import com.example.book.model.FileStorage;
import com.example.book.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    @Autowired
    FileStorageRepository fileStorageRepository;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public FileStorage uploadFile(MultipartFile file) {
        System.out.println("------------------------"+file.getOriginalFilename());
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            FileStorage fileStorage = new FileStorage();

            fileStorage.setDocumentType(file.getContentType());
            fileStorage.setFileName(file.getOriginalFilename());
            fileStorage.setUploadDir(uploadDir);

            fileStorageRepository.save(fileStorage);
            return fileStorage;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
