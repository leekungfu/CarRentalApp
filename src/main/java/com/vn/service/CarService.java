package com.vn.service;

import com.vn.entities.Car;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {
    Page<Car> listAll(int pageNumber, String sortField, String sortDir);
}
