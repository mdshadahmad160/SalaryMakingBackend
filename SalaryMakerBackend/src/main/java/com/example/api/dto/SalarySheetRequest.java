package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalarySheetRequest {
    private String basicSalary;
    private String dateAndYear;
}
