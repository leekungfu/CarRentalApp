package com.vn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.utils.MoneyUtil;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class MemberTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private String type;
    private LocalDateTime date;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;
    public String genAmount(){
        return MoneyUtil.genMoney(this.amount);
    }
}
