package com.vn.dto;

import com.vn.entities.MemberTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WalletResponseDTO {
    private  String message;
    private String balance;
    private List<MemberTransaction> tran;
}
