package com.vn.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {

    public static List<String> saveImages(List<MultipartFile> files) {
        List<String> imagePaths = new ArrayList<>();

        try {
            for (MultipartFile file : files
            ) {
                String imagePath = saveImage(file);
                if (!imagePath.isEmpty()) {
                    imagePaths.add(imagePath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePaths;
    }

    public static String saveImage(MultipartFile multipartFile) {
        try {
            String originName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String newName = System.currentTimeMillis() + originName;
            Path folderPath = Paths.get("./src/main/resources/static/images/");
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            Path imagePath = folderPath.resolve(newName);
            Files.copy(multipartFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + newName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}