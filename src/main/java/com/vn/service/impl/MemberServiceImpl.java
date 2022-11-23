package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import com.vn.repository.MemberRepository;
import com.vn.repository.MemberTransactionRepository;
import com.vn.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberTransactionRepository memberTransactionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member updateMember(Member member) {
        log.info("Update user profile {} in the database", member.getEmail());
        return memberRepository.save(member);
    }

    @Override
    public Integer save(Member member) {
        log.info("Saving new member {} to the database", member.getEmail());
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return memberRepository.save(member).getId();
    }

    @Override
    public Member findUserByEmailAndFullName(String email, String fullName) {
        log.info("Find user using email {} and fullName {}", email, fullName);
        return memberRepository.findByEmailAndFullName(email, fullName);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        log.info("Update new token {} for user to reset password via email {}", token, email);
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            member.setResetPasswordToken(token);
            memberRepository.save(member);
        } else {
            throw new UsernameNotFoundException("Email: " + email + " invalid!");
        }
    }

    @Override
    public Member findByResetPasswordToken(String token) {
        log.info("Find user via token {}", token);
        return memberRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(Member member, String newPassword) {
        log.info("Update password{} for user{}", newPassword, member.getEmail());
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        member.setPassword(encodePassword);
        member.setResetPasswordToken(null);
        memberRepository.save(member);

    }

    @Override
    public Member findByEmail(String email) {
        log.info("Find user by email{}", email);
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAll() {
        log.info("Find all users from the database");
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findUserById(Integer id) {
        log.info("Find user by id{}", id);
        return memberRepository.findById(id);

    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.getOne(id);
    }

    @Override
    public Page<MemberTransaction> findByMember(Integer memberId, Pageable pageable) {
        log.info("Find user by id{} and process pagination", memberId);
        return memberTransactionRepository.findByMemberId(memberId,pageable);
    }

    @Override
    public Page<MemberTransaction> findByMemberAndDate(Integer id, LocalDateTime date1, LocalDateTime date2, Pageable pageable) {
        log.info("Find user by id and date & process pagination");
        return memberTransactionRepository.findByMemberAndDate(id, date1, date2, pageable);
    }
}
