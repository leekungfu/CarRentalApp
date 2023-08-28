//package com.vn.controller;
//
//import com.vn.entities.Car;
//import com.vn.entities.Member;
//import com.vn.enums.CarStatus;
//import com.vn.service.CarService;
//import com.vn.service.MemberService;
//import com.vn.service.impl.CustomUserDetails;
//import com.vn.utils.ImageUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.io.IOException;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class CarOwnerController {
//    @Autowired
//    private CarService carService;
//    @Autowired
//    private MemberService memberService;
//
////    @PostMapping("/addCar")
////    @ResponseBody
////    public String addCarForm(@ModelAttribute("car") Car car,
////                             @RequestParam("images") MultipartFile[] multipartFileImage) throws IOException {
////        Car carCheck = carService.findCarByLicensePlate(car.getPlateNumber());
////
////        car.setRegistrationPaper(ImageUtil.saveImage(multipartFile1));
////        car.setCertificate(ImageUtil.saveImage(multipartFile2));
////        car.setInsurance(ImageUtil.saveImage(multipartFile3));
////
////        StringBuilder carImages = new StringBuilder();
////        for (MultipartFile image : multipartFileImage) {
////            carImages.append(ImageUtil.saveImage(image)).append(",");
////        }
////
////        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        Member member = memberService.findById(detail.getMember().getId());
////        car.setMember(member);
////        car.setStatus(CarStatus.AVAILABLE);
////        car.setImages(new String[]{carImages.toString()});
////        carService.saveCar(car);
////        redirectAttributes.addFlashAttribute("message", "Your car add succesfull!");
////
////        return "redirect:/listCar";
////    }
//
//    @GetMapping("/car/edit/{id}")
//    public String getEditCar(@PathVariable("id") Integer id, Model model) {
//        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("fullName", detail.getMember().getFullName());
//        Car car = carService.findCarById(id);
//        model.addAttribute("car", car);
//        model.addAttribute("carStatus", CarStatus.values());
//        return "car/editCar";
//    }
//
//    @PostMapping("/car/edit/{id}")
//    public String submitEditCar(@ModelAttribute Car car, BindingResult result, @PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "car/editCar";
//        }
//
//        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        CarStatus status = car.getStatus();
//        if (status.equals(CarStatus.BOOKED)) {
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
//
//}