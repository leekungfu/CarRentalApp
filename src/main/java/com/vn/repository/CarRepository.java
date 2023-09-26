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
    @Query(value = "SELECT DISTINCT c FROM Car c " +
            "LEFT JOIN c.bookings b " +
            "WHERE c.province = :province " +
            "AND (b.endDate < :fromTime OR b.endDate IS NULL OR b.bookingStatus ='Completed')" +
            "AND c.status = 'Available'")
    List<Car> findByProvince(String province, LocalDateTime fromTime);
    void deleteById(Integer id);
    List<Car> findCarsByMemberId(Integer id);
    Page<Car> findByMemberId(Integer id, Pageable pageable);
    Car findCarById(Integer id);
}
