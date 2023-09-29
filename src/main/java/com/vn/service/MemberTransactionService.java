package com.vn.service;

import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface MemberTransactionService {
    void save(MemberTransaction memberTransaction);
}
