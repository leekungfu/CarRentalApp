package com.vn.controller;

import com.vn.dto.*;
import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.enums.BookingStatus;
import com.vn.enums.CarStatus;
import com.vn.responses.ResponseBookingResult;
import com.vn.responses.ResponseCarResult;
import com.vn.responses.ResponseCarsBelongToUser;
import com.vn.responses.ResponseMessage;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.FilesStorageService;
import com.vn.service.impl.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OwnerController {
    private final CarService carService;
    private final FilesStorageService filesStorageService;
    private final BookingService bookingService;
    @GetMapping("/cars")
    @ResponseBody
    public ResponseEntity<ResponseCarsBelongToUser> getCarsBelongToUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = customUserDetails.member();
        return ResponseEntity.ok(new ResponseCarsBelongToUser(true, "Get cars successful!", member));
    }
    @PostMapping("/addCar")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> addCarForm(@ModelAttribute CarDto dto, @RequestParam("documents") MultipartFile[] documents, @RequestParam("images") MultipartFile[] images) throws IOException {
        Car result = carService.findCarByLicensePlate(dto.getPlateNumber());
        if (result != null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Car is existed! Try another please.", null, null));
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
        return ResponseEntity.ok(new ResponseCarResult(true, "Save car successful!", car, filesStorageService.findFilesByCarId(car.getId())));
    }
    @PostMapping("/updateDetails/{id}")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> updateCarInfo(@ModelAttribute CarDto dto, @RequestParam("images") MultipartFile[] files, @PathVariable Integer id) throws IOException {
        Car result = carService.findById(id);
        if (result == null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Car is not exist.", null, null));
        }
        result.setMileage(dto.getMileage());
        result.setFuelConsumption(dto.getFuelConsumption());
        result.setProvince(dto.getProvince());
        result.setDistrict(dto.getDistrict());
        result.setWard(dto.getWard());
        result.setStreet(dto.getStreet());
        result.setDescription(dto.getDescription());
        result.setAdditionalFunctions(dto.getAdditionalFunctions());
        carService.update(result);
        for (MultipartFile document : files) {
            filesStorageService.store(document, result);
        }
        return ResponseEntity.ok(new ResponseCarResult(true, "Update successfully!", result, filesStorageService.findFilesByCarId(result.getId())));
    }
    @PostMapping("/updatePricing/{id}")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> updateCarInfo(@ModelAttribute CarDto dto, @PathVariable Integer id) {
        Car result = carService.findById(id);
        if (result == null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Car is not exist.", null, null));
        }
        result.setPrice(dto.getBasePrice());
        result.setDeposit(dto.getDeposit());
        result.setTerms(dto.getTerms());
        carService.update(result);
        return ResponseEntity.ok(new ResponseCarResult(true, "Update successfully!", result, filesStorageService.findFilesByCarId(result.getId())));
    }

    @PostMapping("/updateBookingStatus/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBookingStatus(@PathVariable Integer id) {
        Booking result = bookingService.findBookingById(id);
        if (result == null) {
            return ResponseEntity.ok(new ResponseMessage(false, "Booking is not exist"));
        }
        result.setBookingStatus(BookingStatus.Confirmed);
        bookingService.updateBooking(result);
        return ResponseEntity.ok(new ResponseBookingResult(true, "The deposit is confirmed!", result));
    }
}