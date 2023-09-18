package com.vn.repository;

import com.vn.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByPlateNumber(String plateNumber);
    @Query(value = "SELECT c FROM Car c " +
            "LEFT JOIN c.bookings b " +
            "WHERE c.province = :province " +
            "AND (b.endDate < :fromTime OR b.endDate IS NULL)")
    List<Car> findByProvince(String province, LocalDateTime fromTime);
    Page<Car> findByProvince(String city, Pageable pageable);
    void deleteById(Integer id);
    List<Car> findCarsByMemberId(Integer id);
    Page<Car> findByMemberId(Integer id, Pageable pageable);
    Car findCarById(Integer id);
    @Query(value = "SELECT c FROM Car c LEFT JOIN c.bookings b WHERE c.province = :city AND (b.endDate < :date OR b.endDate IS NULL)")
    Page<Car> findByProvinceAndDate(String city, LocalDate date, Pageable pageable);
}
