package com.vn.dto;

import com.vn.entities.Car;
import com.vn.entities.Files;
import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Getter
@Setter
public class CarDto {
    private String plateNumber;
    private String color;
    private String brand;
    private String model;
    private String productionYear;
    private String numberOfSeat;
    private String transmissionType;
    private String fuelType;
    private Double mileage;
    private Double fuelConsumption;
    private String province;
    private String district;
    private String ward;
    private String street;
    private String description;
    private List<String> additionalFunctions;
    private List<Files> files;
    private Double basePrice;
    private Double deposit;
    private List<String> terms;
    private Double rating;
    private String status;
}