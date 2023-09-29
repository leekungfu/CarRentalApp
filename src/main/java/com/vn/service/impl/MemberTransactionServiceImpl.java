package com.vn.service.impl;

import com.vn.entities.MemberTransaction;
import com.vn.repository.MemberTransactionRepository;
import com.vn.service.MemberTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTransactionServiceImpl implements MemberTransactionService {
    private final MemberTransactionRepository memberTransactionRepository;
    @Override
    public void save(MemberTransaction memberTransaction) {
        memberTransactionRepository.save(memberTransaction);
    }
}
