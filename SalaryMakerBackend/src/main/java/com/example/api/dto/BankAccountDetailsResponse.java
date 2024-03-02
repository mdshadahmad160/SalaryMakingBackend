package com.example.api.dto;

import lombok.Data;

@Data
public class BankAccountDetailsResponse {
    private String accountNo;
    private String accountHolderName;
    private String accountType;
    private Double currentBalance;
    private String bankName;
    private String branchName;
}
