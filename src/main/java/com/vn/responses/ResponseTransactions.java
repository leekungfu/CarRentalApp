package com.vn.responses;

import com.vn.dto.MemberTransactionDto;

import java.util.List;

public class ResponseTransactions {
    public Boolean isSuccess;
    public String message;
    public List<MemberTransactionDto> transactions;

    public ResponseTransactions(Boolean isSuccess, String message, List<MemberTransactionDto> transactions) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.transactions = transactions;
    }
}
