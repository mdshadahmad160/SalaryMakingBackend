package com.example.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class SheetResponse {
    private String grade;
    private List<SalaryResponse> salary;
}
