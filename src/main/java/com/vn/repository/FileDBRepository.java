package com.vn.repository;

import com.vn.entities.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<Files, Integer> {
    List<Files> findFilesByCarId(Integer carId);
}
