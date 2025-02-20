package com.example.travellerblog.service;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class ImageStorageServiceImpl implements ImageStorageService{

    private final Path imageDir = Paths.get("images");

    @Override
    @PostConstruct
    public void init() throws IOException {
        if (!Files.exists(imageDir)) Files.createDirectory(imageDir);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        Path filePath = this.imageDir.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        if (!Files.exists(filePath)) {
            Files.copy(file.getInputStream(), filePath);
        }
        // Normalize the path to use forward slashes
        return filePath.toString().replace("\\", "/");
    }

    @Override
    public MultipartFile loadImage(String imgPath, boolean fullPath) throws IOException {
        imgPath = imgPath.replace("\\", "/");
        File imgFile = fullPath ? Paths.get(imgPath).toFile() :this.imageDir.resolve(imgPath).toFile();

        FileItem imgFileItem = new DiskFileItem("file", "image/png", false,
                imgPath, (int)imgFile.length(), imgFile.getParentFile());

        new FileInputStream(imgFile).transferTo(imgFileItem.getOutputStream());
        return new CommonsMultipartFile(imgFileItem);
    }

    @Override
    public void delete(String imgName, boolean fullPath) throws IOException {
        Files.delete(fullPath ? Path.of(imgName) : this.imageDir.resolve(imgName));
    }
}
