package com.vn.responses;

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
    private List<Files> files;

    public ResponseCarResult(Boolean isSuccess, String message, Car car, List<Files> files) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.car = car;
        this.files = files;
    }
}
