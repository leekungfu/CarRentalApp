package com.vn.repository;

import com.vn.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByLicensePlate(String licensePlate);

    Page<Car> findByCity(String city, Pageable pageable);

    void deleteById(Integer id);

//    List<Car> findByMemberId(Integer idMember);

    Page<Car> findByMemberId(Integer id, Pageable pageable);

    Car findCarById(Integer id);
}
