package com.address.ADDRESS.service;

import com.address.ADDRESS.model.dto.AddressDto;
import com.address.ADDRESS.model.dto.AddressRequest;

import java.util.List;

public interface AddressService {

    AddressDto saveAddress(AddressRequest addressRequest);

    AddressDto updateAddress(AddressRequest addressRequest);

    void deleteAddress(Long id);

    AddressDto getSingleAddress(Long id);

    List<AddressDto> getAllAddress();
}
