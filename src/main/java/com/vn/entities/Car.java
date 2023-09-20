package com.vn.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @JsonManagedReference
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
    @OneToMany(mappedBy = "car")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Files> files;

    public CarDto toDto() {
        CarDto carDto = new CarDto();
        carDto.setPlateNumber(this.getPlateNumber());
        carDto.setColor(this.getColor());
        carDto.setBrand(this.getBrand());
        carDto.setModel(this.getModel());
        carDto.setProductionYear(String.valueOf(this.getProductionYear()));
        carDto.setNumberOfSeat(String.valueOf(this.getNumberOfSeat()));
        carDto.setTransmissionType(this.getTransmissionType());
        carDto.setFuelType(this.getFuelType());
        carDto.setMileage(this.getMileage());
        carDto.setFuelConsumption(this.getFuelConsumption());
        carDto.setProvince(this.getProvince());
        carDto.setDistrict(this.getPlateNumber());
        carDto.setWard(this.getPlateNumber());
        carDto.setStreet(this.getPlateNumber());
        carDto.setDescription(this.getPlateNumber());
        carDto.setAdditionalFunctions(this.getAdditionalFunctions());
        carDto.setFiles(this.getFiles());
        carDto.setBasePrice(this.getPrice());
        carDto.setDeposit(this.getDeposit());
        carDto.setTerms(this.getTerms());
        carDto.setRating(this.getRating());
        if (bookings != null) {
            BookingDto bookingDto = new BookingDto();
            bookingDto.setCar(carDto);
        }
        return carDto;
    }
}
