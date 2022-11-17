package com.vn.entities;

import java.time.LocalDate;

import javax.persistence.*;

import com.vn.utils.BookingStatusEnum;
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
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer paymentMethod;

	@Enumerated(EnumType.STRING)
	private BookingStatusEnum bookingStatus;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@OneToOne(mappedBy = "booking")
	private Feedback feedback;
}
