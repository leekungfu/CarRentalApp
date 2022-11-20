package com.vn.service;

import com.vn.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CarService {
    Car findCarByLicensePlate(String licensePlate);

    Car saveCar(Car car);

    List<Car> findAll();

    Page<Car> findByCity(String city, Pageable pageable);

    Car findById(Integer id);

    Page<Car> listAll(int pageNumber, String sortField, String sortDir, Integer id);

//    Page<Car> findByMemberEmail(String email, Pageable pageable);

    Car findByIdCar(Integer id);

    Car update(Car content);

    boolean delete(Integer id);

    public Page<Car> findByCityAndDate(String city, LocalDate date, Pageable pageable);
}
