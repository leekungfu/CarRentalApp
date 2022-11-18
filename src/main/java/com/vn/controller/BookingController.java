package com.vn.controller;

import com.vn.entities.Car;
import com.vn.repository.CarRepository;
import com.vn.utils.CarStatusEnum;
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
    private CarRepository carRepository;

    @GetMapping("/booking/status")
    public String addStatus2(Model model) {
        model.addAttribute("carStatus", CarStatusEnum.values());
        model.addAttribute("car", new Car());

        return "car/booking_status";
    }

    // Confirm deposit
    @PostMapping("/booking/status")
    public String checkAddStatus(@ModelAttribute("car") Car car, Model model, RedirectAttributes redirectAttributes) {

//        Integer bookingId = car.getBookingId();
//        List<Booking> booking = (List<Booking>) bookingRepository.getOne(bookingId);
//
//        car.setBookings(booking);
        car.setStatus(CarStatusEnum.Booked);
        carRepository.save(car);
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

        carRepository.save(car);
        redirectAttributes.addFlashAttribute("messPayment", "Confirm Payment successful");
        model.addAttribute("carStatus", CarStatusEnum.values());

        return "redirect:/booking/status";
    }
}
