package com.vn.responses;

import com.vn.dto.MemberTransactionDto;

import java.util.List;

public class ResponseTransactionResult {
    public Boolean isSuccess;
    public String message;
    public MemberTransactionDto transaction;

    public ResponseTransactionResult(Boolean isSuccess, String message, MemberTransactionDto transaction) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.transaction = transaction;
    }
}
