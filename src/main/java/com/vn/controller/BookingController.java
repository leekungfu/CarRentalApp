package com.vn.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.entities.Member;
import com.vn.service.BookingService;
import com.vn.service.CarService;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.BookingStatusEnum;
import com.vn.utils.CarStatusEnum;

@Controller
public class BookingController {
	
    @Autowired
    CarService carService;
    
    @Autowired
    MemberService memberService;

	@Autowired
	BookingService bookingService;
	
	
	//rent-a-car
	@GetMapping("/rent_car")
	public String bookingProcess(Model model,
								@RequestParam(name = "carID") Integer carID,
								HttpSession httpSession) {
		Car car = carService.findById(carID);
		model.addAttribute("car", car);
		httpSession.setAttribute("carModel", car);
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Member> optional = memberService.findUserById(detail.getId());
		if(optional.isPresent()) {
			model.addAttribute("user", optional.get());
		}		
		
		model.addAttribute("fullName", detail.getFullName());
		return "booking/booking_process";
		
	}
	
	@PostMapping("/booking_result")
	public String bookingSuccessful(@ModelAttribute("payment")Integer paymentMethod,
									@ModelAttribute("startDate") String startDate,
									@ModelAttribute("endDate") String endDate,
									HttpSession httpSession,
									Model model){
		
		Booking booking = new Booking();
		
		Car car = (Car) httpSession.getAttribute("carModel");
		car.setStatus(CarStatusEnum.Booked);
		carService.update(car);
		
		booking.setCar(car);
		booking.setPaymentMethod(paymentMethod);
		booking.setStartDate(LocalDate.parse(startDate));
		booking.setEndDate(LocalDate.parse(endDate));
		
		switch(paymentMethod) {
		case 1:
			booking.setBookingStatus(BookingStatusEnum.Pending_deposit);
			break;
		case 2,3:
			booking.setBookingStatus(BookingStatusEnum.Pending_payment);
			break;
		default: 
			break;
		}
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Member> optional = memberService.findUserById(detail.getId());
		if(optional.isEmpty()) {
			return "redirect:/login";
		} else {
			booking.setMember(optional.get());
		}
		
		bookingService.addBooking(booking);
		
		model.addAttribute("fullName", detail.getFullName());
		model.addAttribute("booking", booking);
		return "booking/booking_successful";
	}
	
	@GetMapping("/booking")
	public String bookingDetail(Model model,
								@RequestParam(name = "booking_id") Integer bookingId) {
		Booking booking = bookingService.findBookingById(bookingId);
		model.addAttribute("booking", booking);
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("fullName", detail.getFullName());
		return "booking/booking_detail";
	}
	
	@GetMapping("/my_booking")
	public String bookingsListByMember(Model model) {
		
		CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Booking> bookings = bookingService.findAllByMemberId(detail.getId());
		model.addAttribute("bookings", bookings);
		
		model.addAttribute("fullName", detail.getFullName());
		
		return "booking/booking_list";
	}

    @GetMapping("/booking/status")
    public String addStatus2(Model model) {
        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("car", new Car());

        return "car/booking_status";
    }

    // Confirm deposit
    @PostMapping("/booking/status")
    public String checkAddStatus(@ModelAttribute("car") Car car, Model model, RedirectAttributes redirectAttributes) {

        car.setStatus(CarStatusEnum.Booked);
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("messDeposit", "Confirm Deposit successful");
        model.addAttribute("carStatus", CarStatusEnum.Booked);

        return "redirect:/car/payment";
    }

    // Confirm payment

    @GetMapping("/car/payment")
    public String confirmPayment(Model model) {
        model.addAttribute("carStatus", CarStatusEnum.Booked);
        model.addAttribute("car", new Car());

        return "car/confirm_payment";
    }

    @PostMapping("/car/payment")
    public String checkConfirmPayment(@ModelAttribute("car") Car car, Model model, RedirectAttributes redirectAttributes) {

        car.setStatus(CarStatusEnum.Available);
//        car.getBookings().sort(new Comparator<Booking>() {
//            @Override
//            public int compare(Booking o1, Booking o2) {
//                return o2.getId() - o1.getId();
//            }
//        });
//        Booking booking = car.getBookings().get(0);
//        booking.setBookingStatus(BookingStatusEnum.Completed);

        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("messPayment", "Confirm Payment successful");
        model.addAttribute("carStatus", CarStatusEnum.values());

        return "redirect:/booking/status";
    }
}
