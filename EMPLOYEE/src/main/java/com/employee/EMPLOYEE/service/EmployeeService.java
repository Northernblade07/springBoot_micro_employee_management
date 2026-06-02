package com.employee.EMPLOYEE.service;

import com.employee.EMPLOYEE.model.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    EmployeeDto updateEmployee(Long id , EmployeeDto employeeDto);

    EmployeeDto getSingleEmployee(Long id);

    List<EmployeeDto> getAllEmployee();
}
