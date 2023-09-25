package com.vn.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.dto.FeedbackDto;
import com.vn.dto.MemberDto;
import com.vn.enums.BookingStatus;
import com.vn.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	@OneToOne(mappedBy = "booking")
	private DriverInformation info;
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonBackReference
	private Member member;
	@ManyToOne
	@JoinColumn(name = "car_id")
	@JsonBackReference
	private Car car;
	@OneToOne(mappedBy = "booking")
	private Feedback feedback;

	public BookingDto toDto() {
		BookingDto dto = new BookingDto();
		dto.setBookingId(String.valueOf(this.getId()));
		dto.setStartDate(String.valueOf(this.getStartDate()));
		dto.setEndDate(String.valueOf(this.getEndDate()));
		dto.setInfo(this.getInfo());
		dto.setPaymentMethod(String.valueOf(this.getPaymentMethod()));
		dto.setBookingStatus(String.valueOf(this.getBookingStatus()));
		if (this.getCar() != null) {
			CarDto carDto = this.getCar().toDto();
			dto.setCar(carDto);
		}
		if (this.getMember() != null) {
			MemberDto memberDto = this.getMember().toDto();
			dto.setMember(memberDto);
		}
		if (this.getFeedback() != null) {
			FeedbackDto feedbackDto = this.getFeedback().toDto();
			dto.setFeedback(feedbackDto);
		}
		return dto;
	}
}
