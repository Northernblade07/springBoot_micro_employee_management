package com.address.ADDRESS.service.impl;

import com.address.ADDRESS.model.dto.AddressDto;
import com.address.ADDRESS.model.dto.AddressRequest;
import com.address.ADDRESS.model.entity.Address;
import com.address.ADDRESS.repository.AddressRepository;
import com.address.ADDRESS.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public AddressDto saveAddress(AddressRequest addressRequest) {
        return null;
    }

    @Override
    public AddressDto updateAddress(AddressRequest addressRequest) {
        return null;
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with this id" + id));

        addressRepository.deleteById(id);
    }

    @Override
    public AddressDto getSingleAddress(Long id) {
        return null;
    }

    @Override
    public List<AddressDto> getAllAddress() {
        return List.of();
    }
}
