package com.vn.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

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
	private Member member;
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	@OneToOne(mappedBy = "booking")
	private Feedback feedback;
}
