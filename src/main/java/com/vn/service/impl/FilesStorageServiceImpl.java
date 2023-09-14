package com.vn.service.impl;

import com.vn.entities.Car;
import com.vn.repository.FileDBRepository;
import com.vn.service.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    @Override
    public void store(@NotNull MultipartFile multipartFile, @NotNull Car car) throws IOException {
        com.vn.entities.Files result = fileDBRepository.findByNameAndCarId(multipartFile.getOriginalFilename(), car.getId());
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
    public List<com.vn.entities.Files> findFilesByCarId(Integer carId) {
        return fileDBRepository.findFilesByCarId(carId);
    }

}
