package com.vn.service;

import com.vn.entities.Member;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Member updateMember(Member member);
}
