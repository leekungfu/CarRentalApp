package com.vn.service;

import com.vn.dto.CarDto;
import com.vn.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CarService {
    Car findCarByLicensePlate(String licensePlate);
    Car saveCar(Car car);
    Car findById(Integer id);
    List<CarDto> findCarsBelongToUser(Integer id);
    Page<Car> listCarByMemberId(Integer id,Pageable pageable);
    void update(Car car);
    boolean delete(Integer id);
    Car findCarById(Integer id);
    List<Car> searchCar(String province, LocalDateTime fromTime);
}
