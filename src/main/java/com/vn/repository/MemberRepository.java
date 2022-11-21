package com.vn.repository;

import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndFullName(String email, String fullName);

    @Query("SELECT m FROM Member m WHERE m.email=?1")
     Member findByEmail(String email);

     Member findByResetPasswordToken(String token);

}
