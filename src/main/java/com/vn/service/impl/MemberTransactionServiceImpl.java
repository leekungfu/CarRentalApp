package com.vn.service.impl;

import com.vn.entities.MemberTransaction;
import com.vn.repository.MemberTransactionRepository;
import com.vn.service.MemberTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberTransactionServiceImpl implements MemberTransactionService {
    @Autowired
    MemberTransactionRepository memberTransactionRepository;
    @Override
    public void save(MemberTransaction mt) {
        memberTransactionRepository.save(mt);
    }
}
