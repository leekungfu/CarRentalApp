package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarByLicensePlate(String licensePlate);

}
