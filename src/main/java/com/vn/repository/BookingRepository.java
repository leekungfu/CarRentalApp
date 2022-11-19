package com.vn.repository;
import com.vn.utils.BookingStatusEnum;
import com.vn.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findAllByMember(Integer memberId);
}
