package com.vn.service;

import com.vn.entities.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MemberService {
    Member updateMember(Member member);

    Integer save(Member member);

    Member findUserByEmailAndFullName(String email, String fullName);

    Optional<Member> findByEmail(String email);
}
