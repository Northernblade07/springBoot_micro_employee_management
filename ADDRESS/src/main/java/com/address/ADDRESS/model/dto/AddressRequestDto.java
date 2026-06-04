package com.address.ADDRESS.model.dto;

import com.address.ADDRESS.model.Enum.AddressType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequestDto {

    private Long id;
    private String street;
    private String city;
    private String country;
    private Long pinCode;
    private AddressType addressType;
}
