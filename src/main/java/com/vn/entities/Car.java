package com.vn.entities;

import java.util.List;

import javax.persistence.*;

import com.vn.enums.CarStatus;
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
    private Integer productionYear;
    private String plateNumber;
    private String color;
    private Integer numberOfSeat;
    private String transmissionType;
    private String fuelType;
    private String registrationPaper;
    private String certificate;
    private String insurance;
    private Double mileage;
    private Double fuelConsumption;
    private String province;
    private String district;
    private String ward;
    private String street;
    private String description;
    private String additionalFunctions;
    private String[] images;
    private Double price;
    private Double deposit;
    private String[] terms;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
