package com.vn.service;

import com.vn.entities.Car;
import com.vn.entities.Files;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public interface FilesStorageService {
    void init();
    void save(MultipartFile file);
//    Files store(MultipartFile multipartFile) throws IOException;

    void store(MultipartFile multipartFile, Car car) throws IOException;

    Optional<Files> getFile(Integer id);
    Stream<Files> getAllFiles();
    Resource load(String filename);
    void deleteAll();
    Stream<Path> loadAll();

    List<Files> findFilesByCarId(Integer carId);
}
