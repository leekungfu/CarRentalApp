package com.vn.dto;


import com.vn.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMemberResult {
    public boolean isSuccess;
    public String message;
    public Member member;
    public String token;
    public ResponseMemberResult(boolean isSuccess, String message, Member member) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.member = member;
    }


}
