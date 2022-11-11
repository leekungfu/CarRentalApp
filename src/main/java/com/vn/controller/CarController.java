package com.vn.controller;

import com.vn.entities.Car;
import com.vn.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carServicel;

    @GetMapping("/listCar")
    public String listCar(Model model) {
        return listByPage(model, 1,"price","asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {
        Page<Car> page = carServicel.listAll(currentPage,sortField,sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        List<Car> carList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("carList", carList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);

        return "list_car";
    }
}
