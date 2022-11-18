package com.vn.controller;

import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.CarStatusEnum;
import com.vn.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CarOwnerController {
    @Autowired
    private CarService carService;
    @Autowired
    private MemberService memberService;

    // List Car
    @GetMapping("/listCar")
    public String listCar(Model model, HttpSession session) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        return listByPage(model, 1, "price", "asc", session);
    }

    // Pagin Car and sorting price Car
    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir, HttpSession session) {

        // List Car by Email Member(role: Car owner)


        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        Page<Car> page = carService.listAll(currentPage, sortField, sortDir, detail.getId());
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
        // Check role Car Owner
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        model.addAttribute("testAddCar", new Car());
        model.addAttribute("carStatus", CarStatusEnum.values());
        return "/car/testAddCar";
    }


    @PostMapping("/testAddCar")
    public String checkAddCar(@Valid @ModelAttribute("testAddCar") Car car, BindingResult result,
                              HttpSession session, Model model) {
        // Validate Add Car
        if (result.hasErrors()) {
            return "/car/testAddCar";
        }
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        Member member = memberService.findById(detail.getId());
        car.setMember(member);

        carService.saveCar(car);
        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("messAddCar", "AddCar is susscessfull");
        return "/car/testAddCar";
    }

    // Edit Car
    @GetMapping("/editCar/{id}")
    public String editCar(Model model, @PathVariable("id") Integer idCar, HttpSession session) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        Car car = carService.findByIdCar(idCar);

        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("editCar", car);
        return "/car/editCar";
    }

    @PostMapping("/editCar/{id}")
    public String editContentById(@PathVariable("id") Integer idCar, @ModelAttribute("editCar") Car car,
                                  HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        // Edit Car by email Member (role: Car owner)
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member m = new Member();
        m.setFullName(detail.getUsername());
        model.addAttribute("user", m);

        Car editCar = carService.findByIdCar(car.getId());
        editCar.setPrice(car.getPrice());
        editCar.setDeposit(car.getDeposit());
        editCar.setStatus(car.getStatus());

        carService.update(editCar);

        redirectAttributes.addFlashAttribute("messEditCar", "Edit car sucessfull");

        return listByPage(model, 1, "price", "asc", session);
    }

    // Delete Car
    @GetMapping("/deleteCar/{id}")
    public String deleteCarById(Model model, @PathVariable("id") Integer idCar, HttpSession session) {
        carService.delete(idCar);
        return listByPage(model, 1, "price", "asc", session);
    }
}
