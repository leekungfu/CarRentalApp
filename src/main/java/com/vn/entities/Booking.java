package com.vn.entities;

import java.time.LocalDate;

import javax.persistence.*;

import com.vn.enums.BookingStatus;
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
