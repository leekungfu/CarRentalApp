package com.vn.entities;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private String addFunction;
	private String images;
	private Double price;
	private Double deposit;
	private String term;
	private String termExtra;
	private Double rating;
	
	@OneToMany(mappedBy = "car")
	private List<Booking> bookings;

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
}
