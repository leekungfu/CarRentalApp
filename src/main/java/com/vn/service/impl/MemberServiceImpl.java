package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.repository.MemberRepository;
import com.vn.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member updateMember(Member member) {
        return null;
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
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
