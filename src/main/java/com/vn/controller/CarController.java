package com.vn.controller;

import com.vn.dto.CarDTO;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/search/car")
    public String searchCarPage(Model model,
                                @RequestParam(name = "city", required = false) String city,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                @RequestParam("sort") Optional<Integer> sort) {
        Member member = new Member();
        member.setFullName("Sava Le");
        member.setEmail("levansang.gthn@gmail.com");
        member.setId(1);
        model.addAttribute("user", member);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        int sortType = sort.orElse(0);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("city", city);
        model.addAttribute("sortType", sortType);

        Pageable pageable;
        switch (sortType) {
            case 1:
                pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("year").descending());
                break;
            case 2:
                pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("price").descending());
                break;
            case 3:
                pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("rating").descending());
                break;
            default:
                pageable = PageRequest.of(currentPage - 1, pageSize);
        }

        Page<Car> resultPage = carService.findByCity(city, pageable);
        List<CarDTO> carDTOs = new ArrayList<>();
        for (Car car : resultPage.getContent()) {
            carDTOs.add(new CarDTO(car));
        }
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("carDTOs", carDTOs);
        model.addAttribute("totolCar", resultPage.getTotalElements());
        model.addAttribute("totolPage", totalPages);


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
        for (CarDTO carDTO: carDTOs) {
            System.out.println(carDTOs.indexOf(carDTO));
        }
        return "car/listCarSearch";
    }
}
