package com.vn.dto;

import com.vn.entities.Car;
import com.vn.entities.Files;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class ResponseCarResult {
    private Boolean isSuccess;
    private String message;
    private Car car;

    public ResponseCarResult(Boolean isSuccess, String message, Car car) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.car = car;
    }
}
