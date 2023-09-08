package com.vn.service;

import com.vn.entities.Files;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface FilesStorageService {
    void init();
    void save(MultipartFile file);
    Files store(MultipartFile multipartFile) throws IOException;
    Files getFile(String id);
    Stream<Files> getAllFiles();
    Resource load(String filename);
    void deleteAll();
    Stream<Path> loadAll();
}
