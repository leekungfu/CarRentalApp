package com.vn.controller;

import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.utils.CarStatusEnum;
import com.vn.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class CarOwnerController {

    @Autowired
    private CarService carService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/Add-Car")
    public String getFormAddCar() {
        return "Carowner/addCar";
    }

    @PostMapping("/Add-Car")
        public String addCarForm (@ModelAttribute("car") Car car, Model model){
            Car carCheck = carService.findCarByLicensePlate(car.getLicensePlate());
            if(carCheck!=null){
                model.addAttribute("msg", "Car is already exits");
                return "Carowner/addCar";
            }
            carService.saveCar(car);
            return "Carowner/homepagecarowner";

    }
    @GetMapping("/List-Car")
    public String getListCar(@ModelAttribute("car") Car car, Model model){
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "Carowner/listCar";
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
        Member m = (Member) session.getAttribute(Const.SESSION_ROLE_CAR_OWNER);
        if (m != null) {
        model.addAttribute("testAddCar", new Car());
        model.addAttribute("carStatus", CarStatusEnum.values());
        return "/car/testAddCar";
        } else {
            return "/car/listCar";
        }
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
