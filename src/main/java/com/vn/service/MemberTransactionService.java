package com.vn.service;

import com.vn.dto.MemberTransactionDto;
import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public interface MemberTransactionService {
    void save(MemberTransaction memberTransaction);
    List<MemberTransactionDto> getByDate(Integer id, LocalDateTime fromTime, LocalDateTime toTime);
}
