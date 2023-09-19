package com.vn.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vn.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private Double mileage;
    private Double fuelConsumption;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String province;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String district;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String ward;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String street;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "car_additional_functions", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "additional_function")
    private List<String> additionalFunctions;
    private Double price;
    private Double deposit;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "car_terms", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "term")
    private List<String> terms;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    @OneToMany(mappedBy = "car")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @OneToMany(mappedBy = "car")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Files> files;
}
