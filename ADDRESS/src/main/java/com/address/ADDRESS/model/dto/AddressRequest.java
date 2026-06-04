package com.address.ADDRESS.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private Long empId;
    private List<AddressRequestDto> addressRequestDtoList;
}
