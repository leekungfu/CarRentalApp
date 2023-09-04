package com.vn.dto;


import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult {
    public boolean isSuccess;
    public String message;
    public Member member;
    public String token;
    public MessageResult(boolean isSuccess, String message, Member member) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.member = member;
    }


}
