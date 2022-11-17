package com.vn.controller;

import com.vn.dto.CarDTO;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.utils.CarStatusEnum;
import com.vn.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private MemberService memberService;

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

                                @RequestParam("size") Optional<Integer> size) {
        model.addAttribute("city", city);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Car> resultPage = carService.findByCity(city, pageable);
        List<CarDTO> carDTOs = new ArrayList<>();
        for (Car car : resultPage.getContent()) {
            carDTOs.add(new CarDTO(car));
        }
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("resultPage", resultPage);
        model.addAttribute("carDTOs", carDTOs);
        model.addAttribute("totolPage", totalPages);
        Car car  = new Car();


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
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("totolPage", totalPages);
        }

        return "car/listCarSearch";

    }

    // List Car
    @GetMapping("/listCar")
    public String listCar(Model model) {
        return listByPage(model, 1, "price", "asc");
    }

    // Pagin car and sorting price car
    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {
        Page<Car> page = carService.listAll(currentPage, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Car> carList = page.getContent();

        model.addAttribute("carList", carList);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "car/listCar";
    }

    // Add Car
    @GetMapping("/testAddCar")
    public String addContentUi(Model model, HttpSession session) {
//        Member m = (Member) session.getAttribute(Const.SESSION_ROLE_CAR_OWNER);
//        if (m != null) {
            model.addAttribute("testAddCar", new Car());
            model.addAttribute("carStatus", CarStatusEnum.values());
            return "/car/testAddCar";
//        } else {
//            return "/car/listCar";
//        }
    }

    @PostMapping("/testAddCar")
    public String checkAddCar(@ModelAttribute("testAddCar") Car car,
                              HttpSession session, Model model, Principal principal) {

//        Member member = (Member) session.getAttribute(Const.SESSION_ROLE_CAR_OWNER);
        Member member = (Member) ((Authentication)principal).getPrincipal();

        member = memberService.findById(member.getId());
        car.setMember(member);

        carService.saveCar(car);
        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("messAddCar", "AddCar is susscessfull");
        return "/car/testAddCar";
    }

    // Edit Car
    @GetMapping(value = { "/editCar/{id}" })
    public String editContent(Model model, @PathVariable("id") Integer idCar, HttpSession session) {
        Car car = carService.findByIdCar(idCar);

        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("editCar", car);
        return "/car/editCar";
    }

    @PostMapping("/editCar")
    public String editContentById(@ModelAttribute("editCar") Car car,
                                  HttpSession session, Model model, RedirectAttributes redirectAttributes) {

//        Member member = (Member) session.getAttribute(Const.SESSION_ROLE_CAR_OWNER);

        Car editContent = carService.findByIdCar(car.getId());
        editContent.setPrice(car.getPrice());
        editContent.setDeposit(car.getDeposit());
        editContent.setStatus(car.getStatus());

        carService.update(editContent);

//        List<Car> list = carService.findByIdMember(member.getId());
//        model.addAttribute("list", list);

        redirectAttributes.addFlashAttribute("messEditCar", "Edit car sucessfull");

        return listCar(model);
    }

    // Delete Car
    @GetMapping("/deleteCar/{id}")
    public String deleteCarById(Model model, @PathVariable("id") Integer idCar, HttpSession session) {
        Member member = (Member) session.getAttribute(Const.SESSION_ROLE_CAR_OWNER);

        carService.delete(idCar);
//        List<Car> list = carService.findByIdMember(member.getId());
//
//        model.addAttribute("list", list);
//        model.addAttribute("messEditContent", "Delete content sucessfull");
        return listByPage(model, 1, "price", "asc");
    }
}
