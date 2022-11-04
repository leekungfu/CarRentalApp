package com.vn.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.entities.Car;
import com.vn.repository.CarRepository;
import com.vn.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService{
	
	@Autowired
	CarRepository carRepository;

	@Override
	public Car saveCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car updateCar(Car car) {
		return carRepository.save(car);
	}

}
