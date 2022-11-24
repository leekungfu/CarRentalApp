package com.vn.repository;

import com.vn.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByLicensePlate(String licensePlate);

    Page<Car> findByCity(String city, Pageable pageable);

    void deleteById(Integer id);

//    List<Car> findByMemberId(Integer idMember);

    Page<Car> findByMemberId(Integer id, Pageable pageable);


    Car findCarById(Integer id);

    @Query(value = "SELECT c FROM Car c LEFT JOIN c.bookings b WHERE c.city = :city AND (b.endDate < :date OR b.endDate IS NULL)")
    Page<Car> findByCityAndDate(String city, LocalDate date, Pageable pageable);

}
