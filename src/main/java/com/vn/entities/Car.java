package com.vn.entities;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.vn.utils.CarStatusEnum;
import com.vn.utils.ValidatedEditCar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

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
	@Min(value = 10,groups = {ValidatedEditCar.class})
	@Max(value = 1000000,groups = {ValidatedEditCar.class})
    @NotNull(groups = {ValidatedEditCar.class})
    @NotBlank(groups = {ValidatedEditCar.class})
	private Double mileage;
	@NotNull
	private Double fuelConsumption;
    @NotEmpty
    @Nationalized
	private String city;
    @NotEmpty
    @Nationalized
	private String district;
    @NotEmpty
    @Nationalized
	private String ward;
    @NotBlank(groups = {ValidatedEditCar.class})
    @Nationalized
	private String street;
    @NotBlank(groups = {ValidatedEditCar.class})
    private String description;
	private String addFunction;
	private String images;
    @NotNull
	@Min(value = 100000)
	@Max(value = 2000000)
	private Double price;
    @NotNull
	@Min(value = 3000000)
	private Double deposit;
	private String term;
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
	public String genDeposit(){
		Locale locale = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return  numberFormat.format(this.price);
	}
	public String genPrice(){
		Locale locale = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return  numberFormat.format(this.deposit);
	}
	public int genRating1(){
		int a  = rating.intValue();
		if ((rating-a)>0.5)
			return a+1;
		return a;
	}
	public int genRating2(){
		int a = rating.intValue();
		double delta = rating -a;
		int result = 0;
		if(delta >0 && delta<=0.5)
			result =1;
		return  result;
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
