package com.example.api.controller;

import com.example.api.dto.AddNewEmployeeRequest;
import com.example.api.dto.SalarySheetRequest;
import com.example.api.dto.SalarySheetResponse;
import com.example.api.entity.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/add")
    public ResponseEntity<Void> addNewEmployee(@RequestBody AddNewEmployeeRequest addNewEmployeeRequest)
    {
        return employeeService.addNewEmployee(addNewEmployeeRequest);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployees()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/salary")
    public ResponseEntity<SalarySheetResponse> getEmployeesSalary(@RequestBody SalarySheetRequest salarySheetRequest)
    {
        return employeeService.getEmployeesSalary(salarySheetRequest);
    }
}
