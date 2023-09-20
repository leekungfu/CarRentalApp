package com.vn.service.impl;

import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.entities.Booking;
import com.vn.entities.Car;
import com.vn.repository.CarRepository;
import com.vn.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    public Car findCarByLicensePlate(String plateNumber) {
        return carRepository.findCarByPlateNumber(plateNumber);
    }
    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
    @Override
    public Car findById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }
    @Override
    public List<CarDto> findCarsBelongToUser(Integer id) {
        List<Car> cars = carRepository.findCarsByMemberId(id);
        List<CarDto> carDtoList =  cars.stream().map(m -> {
            CarDto dto = m.toDto();
            List<BookingDto> bookingDtoList = m.getBookings().stream()
                    .map(Booking::toDto)
                    .collect(Collectors.toList());
            dto.setBookings(bookingDtoList);
            return dto;
        }).collect(Collectors.toList());
        return carDtoList;
    }
    @Override
    public Page<Car> listCarByMemberId(Integer id, Pageable pageable) {
        return carRepository.findByMemberId(id,pageable);
    }
    public Page<Car> listAll(int pageNumber, String sortField, String sortDir, Integer id) {
        Sort sort = Sort.by("price");
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber - 1,4,sort);
        return carRepository.findByMemberId(id,pageable);
    }
    @Override
    public void update(Car car) {
        carRepository.save(car);
    }
    @Override
    public boolean delete(Integer id) {
        carRepository.deleteById(id);
        return true;
    }
    @Override
    public Car findCarById(Integer id) {
        return carRepository.findCarById(id);
        }
    @Override
    public List<Car> searchCar(String province, LocalDateTime fromTime) {
        return carRepository.findByProvince(province, fromTime);
    }
    @Override
    public CarDto getById(Integer id) {
        Car car = carRepository.findCarById(id);
        if (car == null) {
            return null;
        }
        return modelMapper.map(car, CarDto.class);
    }
}
