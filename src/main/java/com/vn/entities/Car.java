package com.vn.entities;

import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty
	private String brand;
    @NotEmpty
	private String model;
    @NotEmpty
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
    @NotEmpty
	private String city;
    @NotEmpty
	private String district;
	private String ward;
	private String street;
	private String description;
	private Integer addFunction;
	private String images;
    @NotEmpty
	private Double price;
    @NotEmpty
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

	public String[] genImage(){
		String[] imagesArray = new String[3];
		StringTokenizer stz = new StringTokenizer(images,",");
		imagesArray[0] = stz.nextToken();
		imagesArray[1] = stz.nextToken();
		imagesArray[2] = stz.nextToken();
		return imagesArray;
	}
    @Transient
    public String getName() {
        return this.brand + " " + this.model + " " + this.year;
    }

    @Transient
    public String getAddress() {
        return this.district + ", " + this.city;
    }

}
