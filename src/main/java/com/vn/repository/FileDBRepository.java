package com.vn.repository;

import com.vn.entities.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<Files, String> {

}
