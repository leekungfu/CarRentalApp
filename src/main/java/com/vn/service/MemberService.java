package com.vn.service;

import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public interface MemberService {
    Member updateMember(Member member);

    Integer save(Member member);

    Member findUserByEmailAndFullName(String email, String fullName);

    void updateResetPasswordToken(String token, String email);
    Member findByResetPasswordToken(String token);
    void updatePassword(Member member, String newPassword);

    Member findByEmail(String email);
    List<Member> findAll();
    Optional<Member> findUserById(Integer id);
    Member findById(Integer id);

    Page<MemberTransaction> findByMember(Integer memberId, Pageable pageable);

    Page<MemberTransaction> findByMemberAndDate(Integer id, LocalDateTime date1, LocalDateTime date2, Pageable pageable);

    void updateWallet(Member member);

}
