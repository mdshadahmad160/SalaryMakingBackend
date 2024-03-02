package com.example.api.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryResponse {
    private String employeeId;
    private String employeeName;
    private String employeePhoneNo;
    private double basic;
    private double houseRent;
    private double medicalAllowance;
    private double totalSalary;
}
