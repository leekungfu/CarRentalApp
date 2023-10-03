package com.vn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.dto.BookingDto;
import com.vn.dto.CarDto;
import com.vn.dto.MemberDto;
import com.vn.dto.MemberTransactionDto;
import com.vn.enums.PaymentMethod;
import com.vn.enums.Type;
import com.vn.utils.MoneyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private Type type;
    private LocalDateTime dateTime;
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    public MemberTransactionDto toDto() {
        MemberTransactionDto dto = new MemberTransactionDto();
        dto.setId(this.getId());
        dto.setAmount(this.getAmount());
        dto.setType(String.valueOf(this.getType()));
        dto.setDateTime(String.valueOf(this.getDateTime()));
        if (this.getMember() != null) {
            MemberDto memberDto = this.getMember().toDto();
            dto.setMember(memberDto);
        }
        if (this.getBooking() != null) {
            BookingDto bookingDto = this.getBooking().toDto();
            dto.setBooking(bookingDto);
        }
        return dto;
    }
}
