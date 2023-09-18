package com.vn.controller.sangnt24;

import com.vn.entities.Car;
import com.vn.enums.CarStatus;
import com.vn.service.CarService;
import com.vn.service.impl.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CarrrOwnerController {
    @Autowired
    private CarService carService;

    // List Car
    @GetMapping("/listCar")
    public String listCarByMemberId(Model model,
                                    @RequestParam(name = "id", required = false) Integer id,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("sort") Optional<String> sort) {

        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.member().getFullName());

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String sortType = sort.orElse("none");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("id", id);
        model.addAttribute("sortType", sortType);

        Pageable pageable = switch (sortType) {
            case "ascPrice" -> PageRequest.of(currentPage - 1, pageSize, Sort.by("price").ascending());
            case "descPrice" -> PageRequest.of(currentPage - 1, pageSize, Sort.by("price").descending());
            default -> PageRequest.of(currentPage - 1, pageSize);
        };

        Page<Car> resultPage = carService.listCarByMemberId(detail.member().getId(), pageable);

        List<Car> carList = resultPage.getContent();

        long totalItems = resultPage.getTotalElements();
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("carList", carList);
        model.addAttribute("resultPage", resultPage);
        model.addAttribute("totalPage", totalPages);
        model.addAttribute("totalItems", totalItems);

        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    start = end - 4;
                } else {
                    if (start == 1) {
                        end = start + 4;
                    }
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "car/listCar";
    }
    
    // Delete Car
    @GetMapping("/deleteCar/{id}")
    public String deleteCarById(@PathVariable("id") Integer idCar,
                                RedirectAttributes redirectAttributes) {
        carService.delete(idCar);
        redirectAttributes.addFlashAttribute("message", "Delete car successfully");
        return "redirect:/listCar";
    }

    @GetMapping("/confirmDeposit/{id}")
    public String confirmDeposit(Model model, @PathVariable("id") Integer id) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.member().getFullName());

        Car car = carService.findCarById(id);
        car.setStatus(CarStatus.Booked);
        model.addAttribute("car", car);
        model.addAttribute("carStatus", CarStatus.Booked);
        model.addAttribute("messageConfirmDeposit", "Change car status and deposit successfully");
        carService.saveCar(car);
        return "/car/confirmPayment";
    }

    @GetMapping("/confirmPayment/{id}")
    public String confirmPayment(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.member().getFullName());

        Car car = carService.findCarById(id);
        car.setStatus(CarStatus.Available);
//        car.getBookings().sort(new Comparator<Booking>() {
//            @Override
//            public int compare(Booking o1, Booking o2) {
//                return o2.getId() - o1.getId();
//            }
//        });
//        Booking booking = car.getBookings().get(0);
//        booking.setBookingStatus(BookingStatusEnum.Completed);

        carService.saveCar(car);
        model.addAttribute("car", car);
        model.addAttribute("carStatus", CarStatus.values());
        redirectAttributes.addFlashAttribute("message", "Change car status and payment successfully");
        return "redirect:/listCar";
    }

}
