package com.vn.service.impl;

import com.vn.dto.MemberTransactionDto;
import com.vn.entities.MemberTransaction;
import com.vn.repository.MemberTransactionRepository;
import com.vn.service.MemberTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTransactionServiceImpl implements MemberTransactionService {
    private final MemberTransactionRepository memberTransactionRepository;
    @Override
    public void save(MemberTransaction memberTransaction) {
        memberTransactionRepository.save(memberTransaction);
    }

    @Override
    public List<MemberTransactionDto> getByDate(Integer id, LocalDateTime fromTime, LocalDateTime toTime) {
        List<MemberTransaction> memberTransactions = memberTransactionRepository.findByMemberAndDate(id, fromTime, toTime);
        List<MemberTransactionDto> list = memberTransactions.stream().map(MemberTransaction::toDto).toList();
        return list;
    }
}
