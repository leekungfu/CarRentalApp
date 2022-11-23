package com.vn.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImageUtil {
    public static String saveImage(MultipartFile multipartFile){
        try {
            String originName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String newName  = System.currentTimeMillis() + originName;
            Path folderPath = Paths.get("./src/main/resources/static/images/");
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            Path imagePath = folderPath.resolve(newName);
            Files.copy(multipartFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return  "/images/" + newName;
        }catch (Exception e){
            e.printStackTrace();
            return  "";
        }
    }
}