package com.example.book.service;

import com.example.book.exception.FileStorageException;
import com.example.book.model.FileStorage;
import com.example.book.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileStorageService {

    @Autowired
    FileStorageRepository fileStorageRepository;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public FileStorage uploadFile(MultipartFile file) {
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

    public ArrayList downloadFile(String resourceId) throws IOException {
        FileStorage fileStorage = fileStorageRepository.findById(resourceId).orElse(null);

        File file = new File(uploadDir + File.separator + fileStorage.getFileName());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        ArrayList response = new ArrayList();
        response.add(fileStorage);
        response.add(resource);
        return response;
    }
}
