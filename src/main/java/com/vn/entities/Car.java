package com.vn.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
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
    @ElementCollection
    @JsonIgnore
//    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "car_documents", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "document")
    private List<String> documents;
    private Double mileage;
    private Double fuelConsumption;
    private String province;
    private String district;
    private String ward;
    private String street;
    private String description;
    @ElementCollection
    @JsonIgnore
//    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "car_additional_functions", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "additional_function")
    private List<String> additionalFunctions;
    @ElementCollection
    @JsonIgnore
//    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "image")
    private List<String> images;
    private Double price;
    private Double deposit;
    @ElementCollection
    @JsonIgnore
//    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "car_terms", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "term")
    private List<String> terms;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
//    @Fetch(FetchMode.JOIN)
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
}
