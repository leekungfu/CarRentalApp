package com.vn.controller.sanglv;

import com.vn.entities.Car;
import com.vn.service.CarService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.DateTimeUtil;
import com.vn.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CarControllerSangLV {
    @Autowired
    private CarService carService;

    @GetMapping("/search_car")
    public String searchCarPage(Model model,
                                @RequestParam(name = "city", required = false, defaultValue = "") String city,
                                @RequestParam(name = "date1", required = false, defaultValue = "") String date1,
                                @RequestParam(name = "date2", required = false, defaultValue = "") String date2,
                                @RequestParam(name = "time1", required = false, defaultValue = "") String time1,
                                @RequestParam(name = "time2", required = false, defaultValue = "") String time2,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                @RequestParam("sort") Optional<Integer> sort) {

        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.member().getFullName());
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        int sortType = sort.orElse(0);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("city", city);
        model.addAttribute("date1", DateTimeUtil.genDate(date1));
        model.addAttribute("date2", DateTimeUtil.genDate(date2));
        model.addAttribute("time1", DateTimeUtil.genTime(time1));
        model.addAttribute("time2", DateTimeUtil.genTime(time2));

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

        Page<Car> resultPage = carService.findByCityAndDate(city, DateTimeUtil.genD(date1), pageable);
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("resultPage", resultPage);
        model.addAttribute("totolPage", totalPages);

        if (totalPages > 0) {
            model.addAttribute("pageNumbers", PagingUtil.genPageList(totalPages,currentPage));
        }
        return "car/listCarSearch";
    }
}
