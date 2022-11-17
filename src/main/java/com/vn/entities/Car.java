package com.vn.entities;

import java.util.List;

import javax.persistence.*;

import com.vn.utils.CarStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String brand;
	private String model;
	private Integer year;
	private String licensePlate;
	private String color;
	private Integer seat;
	private String transmission;
	private String fuel;
	private String registration;
	private String inspection;
	private String insuranceUrl;
	private Double mileage;
	private Double fuelConsumption;
	private String city;
	private String district;
	private String ward;
	private String street;
	private String description;
	private Integer addFunction;
	private String images;
	private Double price;
	private Double deposit;
	private Integer term;
	private String termExtra;
	private Double rating;

    @Enumerated(EnumType.STRING)
    private CarStatusEnum status;
	
	@OneToMany(mappedBy = "car")
	private List<Booking> bookings;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

    @Transient
    public String getName() {
        return this.brand + " " + this.model + " " + this.year;
    }

    @Transient
    public String getAddress() {
        return this.district + ", " + this.city;
    }


}
