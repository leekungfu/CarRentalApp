package com.vn.controller;

import com.vn.utils.BookingStatusEnum;
import com.vn.entities.Booking;
import com.vn.repository.BookingRepository;
import com.vn.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/status")
    public String addStatus(Model model) {
        model.addAttribute("bookingStatus", BookingStatusEnum.values());
        model.addAttribute("bookingObject", new Booking());

//        BookingStatusEnum bookingStatusEnum = BookingStatusEnum.valueOf("Available");
//        bookingStatusEnum.toString();
//        bookingStatusEnum.name();
//        model.addAttribute("bookingStatusList", new Booking());
        return "booking_status";
    }


    @PostMapping("/status")
    public String checkAddStatus(@ModelAttribute("bookingObject") Booking booking,
                                 Model model, RedirectAttributes redirectAttributes) {

//        booking.setStatus(BookingStatusEnum.Available);

        bookingRepository.save(booking);
        model.addAttribute("messSave", "Save booking successful");
        model.addAttribute("bookingStatus", BookingStatusEnum.values());

        return "booking_status";
    }


}
