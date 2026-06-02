package com.employee.EMPLOYEE.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDto {
    private Long id;
    private String name;
    private String empEmail;
    private String empCode;
    private String companyName;

    public EmployeeDto(Long id, String name, String empEmail, String empCode, String companyName) {
        this.id = id;
        this.name = name;
        this.empEmail = empEmail;
        this.empCode = empCode;
        this.companyName = companyName;
    }
}
