package com.vn.responses;

import com.vn.entities.Car;
import lombok.Data;

import java.util.List;

@Data
public class ResponseSearchCar {
    private Boolean isSuccess;
    private String message;
    private List<Car> cars;

    public ResponseSearchCar(Boolean isSuccess, String message, List<Car> cars) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.cars = cars;
    }
}
