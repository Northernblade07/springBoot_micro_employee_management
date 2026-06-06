package com.address.ADDRESS.service.impl;

import com.address.ADDRESS.client.EmployeeClient;
import com.address.ADDRESS.exception.BadRequestException;
import com.address.ADDRESS.exception.ResourceNotFoundException;
import com.address.ADDRESS.model.dto.AddressDto;
import com.address.ADDRESS.model.dto.AddressRequest;
import com.address.ADDRESS.model.dto.AddressRequestDto;
import com.address.ADDRESS.model.dto.EmployeeDto;
import com.address.ADDRESS.model.entity.Address;
import com.address.ADDRESS.repository.AddressRepository;
import com.address.ADDRESS.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final EmployeeClient employeeClient;

    @Override
    public List<AddressDto> saveAddress(AddressRequest addressRequest) {

        EmployeeDto employee =  employeeClient.getSingleEmployee(addressRequest.getEmpId());
        if(employee==null){
            throw new ResourceNotFoundException("Employee not found with id:"+addressRequest.getEmpId());
        }
        List<Address> ListToSave = new ArrayList<>();
        for (AddressRequestDto addressRequestDto : addressRequest.getAddressRequestDtoList()){

            Address address = modelMapper.map(addressRequestDto, Address.class);
            address.setEmpId(addressRequest.getEmpId());
            ListToSave.add(address);
        }

       List<Address> savedAddress =  addressRepository.saveAll(ListToSave);

        return savedAddress.stream().map(address -> modelMapper.map(address , AddressDto.class)).toList();
    }

    @Override
    public List<AddressDto> updateAddress(AddressRequest addressRequest) {
        if(addressRequest.getEmpId() == null){
            throw new BadRequestException("Employee id cannot be null");
        }

        EmployeeDto employee = employeeClient.getSingleEmployee(addressRequest.getEmpId());
        if (employee==null){
            throw new ResourceNotFoundException("Employee not found with id:"+addressRequest.getEmpId());
        }

        List<Address> existingAddresses = addressRepository.findAllByEmpId(addressRequest.getEmpId());

        List<Long> requestIds = addressRequest.getAddressRequestDtoList().stream()
                .map(addressRequestDto -> addressRequestDto.getId())
                .filter(Objects::nonNull).toList();

        existingAddresses.stream().filter(address -> !requestIds.contains(address.getId()))
                .forEach(address -> addressRepository.delete(address));

        List<Address> AddressToSave = new ArrayList<>();

        for (AddressRequestDto addressRequestDto : addressRequest.getAddressRequestDtoList()){
            if (addressRequestDto.getId()==null){
                Address address = modelMapper.map(addressRequestDto , Address.class);
                address.setEmpId(addressRequest.getEmpId());
                AddressToSave.add(address);
            } else{

                Address existingAdress = addressRepository.findById(addressRequestDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Address not found with this id" + addressRequestDto.getId()));

                existingAdress.setStreet(addressRequestDto.getStreet());
                existingAdress.setCity(addressRequestDto.getCity());
                existingAdress.setCountry(addressRequestDto.getCountry());
                existingAdress.setPinCode(addressRequestDto.getPinCode());
                existingAdress.setAddressType(addressRequestDto.getAddressType());

                AddressToSave.add(existingAdress);
            }
        }

       List<Address> savedAddress =  addressRepository.saveAll(AddressToSave);

        return savedAddress.stream().map((element) -> modelMapper.map(element, AddressDto.class)).toList();

    }

    @Override
    public void deleteAddress(Long id) {
       if (id == null){
           throw new BadRequestException("Id cannot be null");
       }
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with this id" + id));

        addressRepository.deleteById(id);
    }



    @Override
    public AddressDto getSingleAddress(Long id) {
        if (id == null){
            throw new BadRequestException("Provide proper id");
        }
        Address address = addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No address exits with this id"+id));
        return modelMapper.map(address , AddressDto.class);
    }

    @Override
    public List<AddressDto> getAllAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(address -> modelMapper.map(address , AddressDto.class)).toList();
    }

    @Override
    public void deleteAllByEmpId(Long empId) {
        if (empId == null){
            throw new BadRequestException("Employee id cannot be null");
        }
        addressRepository.deleteAllByEmpId(empId);
    }

    @Override
    public List<AddressDto> getAddressByEmployeeId(Long empId) {
        if (empId == null){
            throw new BadRequestException("Employee id cannot be null");
        }
        List<Address> addressList = addressRepository.findAllByEmpId(empId);

        return addressList.stream().map(address -> modelMapper.map(address , AddressDto.class)).toList();
    }
}
