package com.vn.responses;

import com.vn.entities.Car;
import com.vn.entities.Files;
import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseCarsBelongToUser {
    private Boolean isSuccess;
    private String message;
    private Member member;
    private List<Car> cars;
}
