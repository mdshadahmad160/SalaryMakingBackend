package com.example.api.controller;

import com.example.api.dto.AddMoneyRequest;
import com.example.api.dto.BankAccountDetailsResponse;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/accounts/{accountNo}/details")
    public ResponseEntity<BankAccountDetailsResponse> getMyAccountDetails(@PathVariable String accountNo){
        return employeeService.getMyAccountDetails(accountNo);
    }
    @PutMapping("/add/balance")
    public ResponseEntity<Void> addBalance(@RequestBody AddMoneyRequest addMoneyRequest){
         employeeService.addBalanceInBank(addMoneyRequest.getAccountNo(),addMoneyRequest.getAmount());
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
