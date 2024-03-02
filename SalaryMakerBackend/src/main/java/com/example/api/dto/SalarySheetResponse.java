package com.example.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalarySheetResponse {
    private String monthAndYear;
    private double totalPaidSalary;
    private double remainBankBalance;
    private List<SheetResponse> sheet;
}
