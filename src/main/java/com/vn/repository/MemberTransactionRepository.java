package com.vn.repository;

import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberTransactionRepository extends JpaRepository<MemberTransaction, Integer> {
    List<MemberTransaction> findAllByMemberId(Integer memberId);
    @Query("SELECT mt FROM MemberTransaction mt WHERE mt.member.id = :id AND mt.dateTime>= :fromTime AND mt.dateTime <= :toTime")
    List<MemberTransaction> findByMemberAndDate(Integer id, LocalDateTime fromTime, LocalDateTime toTime);
}
