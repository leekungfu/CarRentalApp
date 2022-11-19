package com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vn.entities.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
