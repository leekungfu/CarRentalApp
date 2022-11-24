package com.vn.service;

import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;

public interface MemberTransactionService {
    void save(MemberTransaction mt);
}
