package com.vn.repository;

import com.vn.entities.MemberTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface MemberTransactionRepository extends JpaRepository<MemberTransaction, Integer> {
    Page<MemberTransaction> findByMemberId(Integer memberID, Pageable pageable);
    @Query("SELECT mt FROM MemberTransaction mt WHERE mt.member.id = :id AND mt.dateTime>= :date1 AND mt.dateTime <= :date2")
    Page<MemberTransaction> findByMemberAndDate(Integer id, LocalDateTime date1, LocalDateTime date2, Pageable pageable);
}
