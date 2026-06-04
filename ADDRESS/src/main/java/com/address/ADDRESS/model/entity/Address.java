package com.address.ADDRESS.model.entity;

import com.address.ADDRESS.model.Enum.AddressType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long empId;
    private String street;
    private String city;

    private String country;
    private Long pinCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
