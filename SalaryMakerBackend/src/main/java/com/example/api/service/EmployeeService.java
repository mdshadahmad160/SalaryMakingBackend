package com.example.api.service;
import com.example.api.dto.*;
import com.example.api.entity.Bank;
import com.example.api.entity.Employee;
import com.example.api.repository.BankRepository;
import com.example.api.repository.EmployeeRepository;
import com.example.api.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BankRepository bankRepository;

    public ResponseEntity<Void> addNewEmployee(AddNewEmployeeRequest addNewEmployeeRequest){
        Bank bank=new Bank();
        bank.setAccountNo(addNewEmployeeRequest.getAccountNo());
        bank.setAccountType(addNewEmployeeRequest.getAccountType());
        bank.setAccountHolderName(addNewEmployeeRequest.getAccountNo());
        bank.setBankName(addNewEmployeeRequest.getBankName());
        bank.setCurrentBalance(0.0);
        bank.setBranchName(addNewEmployeeRequest.getAccountNo());

        Employee employee=new Employee();
        employee.setEmployeeId(String.valueOf(Utility.getRandomId()));
        employee.setEmployeeAddress(addNewEmployeeRequest.getAddress());
        employee.setEmployeeMobileNo(addNewEmployeeRequest.getPhoneNo());
        employee.setEmployeeName(addNewEmployeeRequest.getName());
        employee.setGradeOfEmployee(addNewEmployeeRequest.getRank());
        employee.setBank(bank);
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Employee>> getAllEmployees(){
        return  new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
    }
    public ResponseEntity<SalarySheetResponse> getEmployeesSalary(SalarySheetRequest salarySheetRequest){

        List<SheetResponse> sheetResponseList=new ArrayList<>();
        List<SalaryResponse> salaryResponseOneList = new ArrayList<>();
        List<SalaryResponse> salaryResponseTwoList = new ArrayList<>();
        List<SalaryResponse> salaryResponseThreeList = new ArrayList<>();
        List<SalaryResponse> salaryResponseFourList = new ArrayList<>();
        List<SalaryResponse> salaryResponseFiveList = new ArrayList<>();
        List<SalaryResponse> salaryResponseSixList = new ArrayList<>();

        List<Employee> employeeList=employeeRepository.findAll();
        double  totalPaidSalary=0.0;
        for (Employee employee : employeeList) {
            System.out.println(employee.getGradeOfEmployee().toLowerCase());
            SalaryResponse salaryResponse = null;
            if (employee.getGradeOfEmployee().toLowerCase().equals("grade 6")) {
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), Double.valueOf(salarySheetRequest.getBasicSalary()), employee.getBank().getAccountNo());

                salaryResponseSixList.add(salaryResponse);
            } else if (employee.getGradeOfEmployee().toLowerCase().equals("grade 5")) {
                double mySalary = Double.valueOf(salarySheetRequest.getBasicSalary()) + (5000 * (6 - Double.valueOf(employee.getGradeOfEmployee().substring(employee.getGradeOfEmployee().length() - 1))));
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), mySalary, employee.getBank().getAccountNo());
                salaryResponseFiveList.add(salaryResponse);
            } else if (employee.getGradeOfEmployee().toLowerCase().equals("grade 4")) {
                double mySalary = Double.valueOf(salarySheetRequest.getBasicSalary()) + (5000 * (6 - Double.valueOf(employee.getGradeOfEmployee().substring(employee.getGradeOfEmployee().length() - 1))));
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), mySalary, employee.getBank().getAccountNo());
                salaryResponseFourList.add(salaryResponse);
            } else if (employee.getGradeOfEmployee().toLowerCase().equals("grade 3")) {
                double mySalary = Double.valueOf(salarySheetRequest.getBasicSalary()) + (5000 * (6 - Double.valueOf(employee.getGradeOfEmployee().substring(employee.getGradeOfEmployee().length() - 1))));
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), mySalary, employee.getBank().getAccountNo());
                salaryResponseThreeList.add(salaryResponse);
            } else if (employee.getGradeOfEmployee().toLowerCase().equals("grade 2")) {
                double mySalary = Double.valueOf(salarySheetRequest.getBasicSalary()) + (5000 * (6 - Double.valueOf(employee.getGradeOfEmployee().substring(employee.getGradeOfEmployee().length() - 1))));
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), mySalary, employee.getBank().getAccountNo());
                salaryResponseTwoList.add(salaryResponse);
            } else if (employee.getGradeOfEmployee().toLowerCase().equals("grade 1")) {
                double mySalary = Double.valueOf(salarySheetRequest.getBasicSalary()) + (5000 * (6 - Double.valueOf(employee.getGradeOfEmployee().substring(employee.getGradeOfEmployee().length() - 1))));
                salaryResponse = getSalaryResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeMobileNo(), mySalary, employee.getBank().getAccountNo());
                salaryResponseOneList.add(salaryResponse);
            }
            totalPaidSalary=totalPaidSalary+salaryResponse.getTotalSalary();


        }
        SheetResponse sheetResponseForSix=new SheetResponse();
        sheetResponseForSix.setGrade("6");
        sheetResponseForSix.setSalary(salaryResponseSixList);
        sheetResponseList.add(sheetResponseForSix);


        SheetResponse sheetResponseForFive=new SheetResponse();
        sheetResponseForFive.setGrade("5");
        sheetResponseForFive.setSalary(salaryResponseFiveList);
        sheetResponseList.add(sheetResponseForFive);


        SheetResponse sheetResponseForFour= new SheetResponse();
        sheetResponseForFour.setGrade("4");
        sheetResponseForFour.setSalary(salaryResponseFourList);
        sheetResponseList.add(sheetResponseForFour);


        SheetResponse sheetResponseForThree=new SheetResponse();
        sheetResponseForThree.setGrade("3");
        sheetResponseForThree.setSalary(salaryResponseThreeList);
        sheetResponseList.add(sheetResponseForThree);


        SheetResponse sheetResponseForTwo=new SheetResponse();
        sheetResponseForTwo.setGrade("2");
        sheetResponseForTwo.setSalary(salaryResponseTwoList);
        sheetResponseList.add(sheetResponseForTwo);


        SheetResponse sheetResponseForOne = new SheetResponse();
        sheetResponseForOne.setGrade("1");
        sheetResponseForOne.setSalary(salaryResponseOneList);
        sheetResponseList.add(sheetResponseForOne);
        System.out.println("-----" + String.valueOf(totalPaidSalary));
        if (getCurrentBalance("42885412") >=totalPaidSalary){
            withdrawBalanceInBank("42885412",String.valueOf(totalPaidSalary));
            SalarySheetResponse salarySheetResponse=new SalarySheetResponse();
            salarySheetResponse.setMonthAndYear(salarySheetRequest.getDateAndYear());
            salarySheetResponse.setTotalPaidSalary(Utility.format(totalPaidSalary));
            salarySheetResponse.setRemainBankBalance(getCurrentBalance("42885412"));
            salarySheetResponse.setSheet(sheetResponseList);
            return new ResponseEntity<>(salarySheetResponse,HttpStatus.OK);

        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public double getCurrentBalance(String accountNo) {
        Optional<Bank> bankOptional=bankRepository.findById(accountNo);
        if (!bankOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank Account Not Found");
        }
        return Double.valueOf(bankOptional.get().getCurrentBalance());
    }

    public SalaryResponse getSalaryResponse(String employeeId, String employeeName,
                                             String employeeMobileNo, double basicSalary,
                                             String accountNo) {
        double houseRent = Utility.format(basicSalary * 0.2);
        double medicalAllowance=Utility.format(basicSalary *0.15);
        double total=Utility.format(basicSalary+houseRent+medicalAllowance);
        SalaryResponse salaryResponse = new SalaryResponse();
        salaryResponse.setEmployeeId(employeeId);
        salaryResponse.setEmployeeName(employeeName);
        salaryResponse.setEmployeePhoneNo(employeeMobileNo);
        salaryResponse.setHouseRent(houseRent);
        salaryResponse.setBasic(basicSalary);
        salaryResponse.setMedicalAllowance(medicalAllowance);
        salaryResponse.setTotalSalary(total);
        addBalanceInBank(accountNo,String.valueOf(total));
        System.out.println("-----" + String.valueOf(total));
        return salaryResponse;

    }

    public void addBalanceInBank(String accountNo, String amount) {
        Optional<Bank> bankOptional=bankRepository.findById(accountNo);
        if (!bankOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank Account Not Found");
        }
        Bank bank=bankOptional.get();
        double currentBalance=Utility.format(Double.valueOf(bank.getCurrentBalance()) + Double.valueOf(amount));
        bank.setCurrentBalance(currentBalance);
        bankRepository.save(bank);

    }
    public void withdrawBalanceInBank(String accountNo, String amount) {
        Optional<Bank> bankOptional=bankRepository.findById(accountNo);
        if (!bankOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank Account Not Found");
        }
        Bank bank=bankOptional.get();
        double currentBalance=Utility.format(Double.valueOf(bank.getCurrentBalance()) - Double.valueOf(amount));
        bank.setCurrentBalance(currentBalance);
        bankRepository.save(bank);


    }
    public ResponseEntity<BankAccountDetailsResponse> getMyAccountDetails(String accountNo){
        Optional<Bank> bankOptional=bankRepository.findById(accountNo);
        if (!bankOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bank Account Not Found");
        }
        Bank bank=bankOptional.get();
        BankAccountDetailsResponse bankAccountDetailsResponse=new BankAccountDetailsResponse();
        bankAccountDetailsResponse.setAccountNo(bank.getAccountNo());
        bankAccountDetailsResponse.setAccountType(bank.getAccountType());
        bankAccountDetailsResponse.setAccountHolderName(bank.getAccountHolderName());
        bankAccountDetailsResponse.setBankName(bank.getBankName());
        bankAccountDetailsResponse.setBranchName(bank.getBranchName());
        bankAccountDetailsResponse.setCurrentBalance(Double.valueOf(bank.getCurrentBalance()));
        return new ResponseEntity<>(bankAccountDetailsResponse,HttpStatus.OK);
    }

}
