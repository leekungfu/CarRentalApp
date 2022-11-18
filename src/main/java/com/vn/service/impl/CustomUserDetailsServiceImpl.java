package com.vn.service.impl;

import com.vn.entities.Member;
import com.vn.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Find user in the DB (with information at login form) by their email to make sure that user exist or not, then determine their role.
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> memberOptional = Optional.ofNullable(memberRepository.findByEmail(email));
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("Email invalid!");
        } else {
            Member member = memberOptional.get();
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole());
//            authorities.add(authority);
            return new CustomUserDetails(member);
        }
    }
}
