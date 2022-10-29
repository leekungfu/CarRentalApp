package com.vn.repository;

import com.vn.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndFullName(String email, String fullName);

    @Query("SELECT m FROM Member m WHERE m.email=?1")
    public Member findByEmail(String email);

    public Member findByResetPasswordToken(String token);
}
