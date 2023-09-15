package com.vn.controller;

import com.vn.dto.ResponseCarResult;
import com.vn.dto.ResponseSearchCar;
import com.vn.entities.Car;
import com.vn.service.CarService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.DateTimeUtil;
import com.vn.utils.PagingUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CarCustomerController {
    private final CarService carService;
    @GetMapping("/searchCar")
    @ResponseBody
    public ResponseEntity<ResponseSearchCar> searchCarByProvince(@RequestParam("selectedProvince") String province, @RequestParam("startTime") String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime time = LocalDateTime.parse(startTime, formatter);
        List<Car> cars = carService.searchCar(province, time);
        return ResponseEntity.ok(new ResponseSearchCar(true, "Search car successful", cars));
    }
    @GetMapping("/getCar/{id}")
    @ResponseBody
    public ResponseEntity<ResponseCarResult> getCarById(@PathVariable Integer id) {
        Car car = carService.findCarById(id);
        if (car == null) {
            return ResponseEntity.ok(new ResponseCarResult(false, "Get car failed", null, null));
        }
        return ResponseEntity.ok(new ResponseCarResult(true, "Get car successful", car, null));
    }
}
