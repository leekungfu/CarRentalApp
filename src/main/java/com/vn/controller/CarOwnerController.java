package com.vn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.dto.*;
import com.vn.entities.Car;
import com.vn.entities.Files;
import com.vn.entities.Member;
import com.vn.enums.CarStatus;
import com.vn.service.CarService;
import com.vn.service.FilesStorageService;
import com.vn.service.impl.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CarOwnerController {
    private final CarService carService;
    private final FilesStorageService filesStorageService;

    @GetMapping("/cars")
    @ResponseBody
    public ResponseEntity<ResponseCarsBelongToUser> getCarsBelongToUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        List<Car> cars = carService.findCarsBelongToUser(member.getId());
        return ResponseEntity.ok(new ResponseCarsBelongToUser(true, "Get cars successful!", member, cars));
    }

    @PostMapping("/addCar")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> addCarForm(@ModelAttribute @NotNull CarDto dto,
                                                        @RequestParam("documents") MultipartFile[] documents,
                                                        @RequestParam("images") MultipartFile[] images) throws IOException {
        Car result = carService.findCarByLicensePlate(dto.getPlateNumber());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Car is existed! Try another please.", null));
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        Car car = new Car();

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

        carService.saveCar(car);

        for (MultipartFile document : documents) {
            filesStorageService.store(document, car);
        }

        for (MultipartFile image : images) {
            filesStorageService.store(image, car);
        }

        return ResponseEntity.ok(new ResponseCarResult(true, "Save car successful!", car));
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = filesStorageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getBase64Data());
        }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        Optional<Files> files = filesStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.get().getName() + "\"")
                .body(files.get().getData());
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