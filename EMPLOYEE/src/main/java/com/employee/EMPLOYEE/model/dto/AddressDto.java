package com.employee.EMPLOYEE.model.dto;


import com.employee.EMPLOYEE.model.Enum.AddressType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private Long empId;
    private String street;
    private String city;

    private String country;
    private Long pinCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
