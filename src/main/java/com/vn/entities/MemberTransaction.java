package com.vn.entities;

import com.vn.utils.MoneyUtil;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
        return MoneyUtil.genMoney(this.amount);
    }
}
