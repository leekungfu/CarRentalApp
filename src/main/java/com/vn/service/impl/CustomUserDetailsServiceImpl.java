package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Email is taken!");
        } else {
            Member member = memberOptional.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
            authorities.add(authority);
            return new User(member.getEmail(), member.getPassword(), authorities);
        }
    }
}
