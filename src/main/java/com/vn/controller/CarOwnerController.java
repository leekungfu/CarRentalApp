package com.vn.controller;

import com.vn.dto.CarDto;
import com.vn.dto.MessageResult;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.enums.CarStatus;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class CarOwnerController {
    @Autowired
    private CarService carService;
    @Autowired
    private MemberService memberService;

    @PostMapping("/addCar")
    @ResponseBody
    public ResponseEntity<?> addCarForm(@ModelAttribute CarDto dto
    ) {
        Car result = carService.findCarByLicensePlate(dto.getPlateNumber());
        if (result != null) {
            return ResponseEntity.ok(new MessageResult(false, "Car is existed! Try another please.", null));
        }

        List<MultipartFile> documentFiles = dto.getDocuments();
        List<MultipartFile> imageFiles = dto.getImages();

        List<String> documents = ImageUtil.saveImages(documentFiles);
        List<String> images = ImageUtil.saveImages(imageFiles);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Member member = customUserDetails.getMember();

        Car car = new Car();
        car.setPlateNumber(dto.getPlateNumber());
        car.setColor(dto.getColor());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setProductionYear(Integer.valueOf(dto.getProductionYear()));
        car.setNumberOfSeat(Integer.valueOf(dto.getNumberOfSeat()));
        car.setTransmissionType(dto.getTransmissionType());
        car.setFuelType(dto.getFuelType());
        car.setDocuments(documents);
        car.setMileage(dto.getMileage());
        car.setFuelConsumption(dto.getFuelConsumption());
        car.setProvince(dto.getProvince());
        car.setDistrict(dto.getDistrict());
        car.setWard(dto.getWard());
        car.setStreet(dto.getStreet());
        car.setDescription(dto.getDescription());
        car.setAdditionalFunctions(dto.getAdditionalFunctions());
        car.setImages(images);
        car.setPrice(dto.getBasePrice());
        car.setDeposit(dto.getDeposit());
        car.setTerms(dto.getTerms());
        car.setRating(dto.getRating());
        car.setStatus(CarStatus.valueOf(dto.getStatus()));
        car.setMember(member);

        carService.saveCar(car);
        return ResponseEntity.ok(new MessageResult(true, "Save car successful!", member));
    }

    @GetMapping("/car/edit/{id}")
    public String getEditCar(@PathVariable("id") Integer id, Model model) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.getMember().getFullName());
        Car car = carService.findCarById(id);
        model.addAttribute("car", car);
        model.addAttribute("carStatus", CarStatus.values());
        return "car/editCar";
    }

    @PostMapping("/car/edit/{id}")
    public String submitEditCar(@ModelAttribute Car car, BindingResult result, @PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "car/editCar";
        }

        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CarStatus status = car.getStatus();
        if (status.equals(CarStatus.BOOKED)) {
            model.addAttribute("messBooked", "Can't change status to Booked");
            model.addAttribute("car", car);
            model.addAttribute("carStatus", CarStatus.values());
            return "car/editCar";
        }
        redirectAttributes.addFlashAttribute("message", "Edit car successful");
        model.addAttribute("carStatus", CarStatus.values());
        carService.saveCar(car);
        return "redirect:/listCar";
    }

}