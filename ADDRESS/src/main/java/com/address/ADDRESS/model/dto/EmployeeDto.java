package com.address.ADDRESS.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String empEmail;
    private String empCode;
    private String companyName;

}
