package com.vn.entities;

import com.vn.utils.GenMoney;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class MemberTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private String type;
    private LocalDateTime date;
    private String note;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    public String genAmount(){
        return GenMoney.genMoney(this.amount);
    }
}
