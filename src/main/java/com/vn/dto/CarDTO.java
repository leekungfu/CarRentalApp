package com.vn.dto;

import com.vn.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Getter
@Setter
public class CarDTO {
    private String plateNumber;
    private String color;
    private Integer brand;
    private String model;
    private String productionYear;
    private String numberOfSeat;
    private String transmissionType;
    private String fuelType;
    private String[] documents;
    private Double mileage;
    private Double fuelConsumption;
    private String province;
    private String district;
    private String ward;
    private String street;
    private String description;
    private String[] additionalFunctions;
    private String[] images;
    private String basePrice;
    private String deposit;
    private String[] terms;
    private Double rating;
}