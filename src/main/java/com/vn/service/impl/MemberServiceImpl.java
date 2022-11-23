package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import com.vn.repository.MemberRepository;
import com.vn.repository.MemberTransactionRepository;
import com.vn.service.MemberService;
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
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberTransactionRepository memberTransactionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member updatePassword(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member) {

        return memberRepository.save(member);
    }

    @Override
    public Integer save(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));

        return memberRepository.save(member).getId();
    }

    @Override
    public Member findUserByEmailAndFullName(String email, String fullName) {
        return memberRepository.findByEmailAndFullName(email, fullName);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
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
        return memberRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(Member member, String newPassword) {
        String encodePassword = bCryptPasswordEncoder.encode(newPassword);
        member.setPassword(encodePassword);
        member.setResetPasswordToken(null);
        memberRepository.save(member);

    }

    @Override
    public Member findByEmail(String email) {

        return memberRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findUserById(Integer id) {
        return memberRepository.findById(id);

    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.getOne(id);
    }

    @Override
    public Page<MemberTransaction> findByMember(Integer memberId, Pageable pageable) {
        return memberTransactionRepository.findByMemberId(memberId,pageable);
    }

    @Override
    public Page<MemberTransaction> findByMemberAndDate(Integer id, LocalDateTime date1, LocalDateTime date2, Pageable pageable) {
        return memberTransactionRepository.findByMemberAndDate(id, date1, date2, pageable);
    }
}
