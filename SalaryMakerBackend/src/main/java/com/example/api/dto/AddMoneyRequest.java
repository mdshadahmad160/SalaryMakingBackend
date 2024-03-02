package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddMoneyRequest {
    private String accountNo;
    private String amount;
}
