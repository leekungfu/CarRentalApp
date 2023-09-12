package com.vn.service.impl;

import com.vn.entities.Car;
import com.vn.repository.FileDBRepository;
import com.vn.service.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements FilesStorageService {
    private final FileDBRepository fileDBRepository;
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void store(@NotNull MultipartFile multipartFile, Car car) throws IOException {
        com.vn.entities.Files result = fileDBRepository.findByName(multipartFile.getOriginalFilename());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (result != null) {
            result.setName(fileName);
            result.setType(multipartFile.getContentType());
            result.setData(multipartFile.getBytes());
            result.setBase64Data(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
            result.setCar(car);
            fileDBRepository.save(result);
        } else {
            com.vn.entities.Files files = new com.vn.entities.Files();
            files.setName(fileName);
            files.setType(multipartFile.getContentType());
            files.setData(multipartFile.getBytes());
            files.setBase64Data(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
            files.setCar(car);
            fileDBRepository.save(files);
        }
    }

    @Override
    public Optional<com.vn.entities.Files> getFile(Integer id) {
        return fileDBRepository.findById(id);
    }

    @Override
    public Stream<com.vn.entities.Files> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public List<com.vn.entities.Files> findFilesByCarId(Integer carId) {
        return fileDBRepository.findFilesByCarId(carId);
    }

}
