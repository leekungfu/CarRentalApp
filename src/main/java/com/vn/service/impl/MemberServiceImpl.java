package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.repository.MemberRepository;
import com.vn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member updateMember( Member member) {
        Member user = memberRepository.findByEmail(member.getEmail());
        if (user == null) {
            return null;
        }
        user.setFullName(member.getFullName());
        user.setPhone(member.getPhone());
        user.setNationalID(member.getNationalID());
        user.setCityID(member.getCityID());
        user.setDistrictID(member.getDistrictID());
        user.setWardID(member.getWardID());
        user.setStreet(member.getStreet());
        return memberRepository.save( member );
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
}
