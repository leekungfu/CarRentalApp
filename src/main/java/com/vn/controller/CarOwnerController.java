package com.vn.controller;

import com.vn.dto.CarDto;
import com.vn.dto.MessageResult;
import com.vn.entities.Car;
import com.vn.entities.Files;
import com.vn.entities.Member;
import com.vn.enums.CarStatus;
import com.vn.service.CarService;
import com.vn.service.FilesStorageService;
import com.vn.service.impl.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarService carService;
    private final FilesStorageService filesStorageService;

    @PostMapping("/addCar")
    @ResponseBody
    public ResponseEntity<?> addCarForm(@ModelAttribute @NotNull CarDto dto,
                                        @RequestParam("documents") MultipartFile[] documents,
                                        @RequestParam("images") MultipartFile[] images) throws IOException {
        Car result = carService.findCarByLicensePlate(dto.getPlateNumber());
        if (result != null) {
            return ResponseEntity.ok(new MessageResult(false, "Car is existed! Try another please.", null));
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        Car car = new Car();
        Files files = new Files();

        List<String> documentFiles = new ArrayList<>();
        for (MultipartFile document : documents) {
            filesStorageService.save(document);
            filesStorageService.store(document);
            documentFiles.add(document.getOriginalFilename());


            // Create a new Files object for each document and set its properties
            Files documentFile = new Files();
            documentFile.setName(document.getOriginalFilename());
            documentFile.setUrl("URL_FOR_DOCUMENT"); // Replace with the actual URL
            documentFile.setCar(car); // Assuming you have a 'car' object already

            // Add the document file to the 'files' list in 'car'
            car.getFiles().add(documentFile);
        }

        List<String> imageFiles = new ArrayList<>();
        for (MultipartFile image : images) {
            filesStorageService.save(image);
            filesStorageService.store(image);
            imageFiles.add(image.getOriginalFilename());


        }

        car.setPlateNumber(dto.getPlateNumber());
        car.setColor(dto.getColor());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setProductionYear(Integer.valueOf(dto.getProductionYear()));
        car.setNumberOfSeat(Integer.valueOf(dto.getNumberOfSeat()));
        car.setTransmissionType(dto.getTransmissionType());
        car.setFuelType(dto.getFuelType());
        car.setMileage(dto.getMileage());
        car.setFuelConsumption(dto.getFuelConsumption());
        car.setProvince(dto.getProvince());
        car.setDistrict(dto.getDistrict());
        car.setWard(dto.getWard());
        car.setStreet(dto.getStreet());
        car.setDescription(dto.getDescription());
        car.setAdditionalFunctions(dto.getAdditionalFunctions());
        car.setPrice(dto.getBasePrice());
        car.setDeposit(dto.getDeposit());
        car.setTerms(dto.getTerms());
        car.setRating(dto.getRating());
        car.setStatus(CarStatus.valueOf(dto.getStatus()));
        car.setMember(member);

        files.setCar(car);
        carService.saveCar(car);
        return ResponseEntity.ok(new MessageResult(true, "Save car successful!", member));
    }

    @GetMapping("/files")
    public ResponseEntity<List<Files>> getListFiles() {
        List<Files> fileInfo = filesStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(CarOwnerController.class, "getFile", path.getFileName().toString()).build().toString();

            return new Files(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

//    @PostMapping("/car/edit/{id}")
//    @ResponseBody
//    public ResponseEntity<?> editCar(@ModelAttribute CarDto dto) {
//        CarStatus status = car.getStatus();
//        if (status.equals(CarStatus.Booked)) {
//            model.addAttribute("messBooked", "Can't change status to Booked");
//            model.addAttribute("car", car);
//            model.addAttribute("carStatus", CarStatus.values());
//            return "car/editCar";
//        }
//        redirectAttributes.addFlashAttribute("message", "Edit car successful");
//        model.addAttribute("carStatus", CarStatus.values());
//        carService.saveCar(car);
//        return "redirect:/listCar";
//    }

}