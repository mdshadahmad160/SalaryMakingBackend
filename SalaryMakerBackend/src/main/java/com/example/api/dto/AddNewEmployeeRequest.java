package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewEmployeeRequest {
    private String name;
    private String rank;
    private String phoneNo;
    private String address;
    private String bankName;
    private String branchName;
    private String accountNo;
    private String accountType;
}
