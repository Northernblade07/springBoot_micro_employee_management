package com.address.ADDRESS.controller;

import com.address.ADDRESS.model.dto.AddressDto;
import com.address.ADDRESS.model.dto.AddressRequest;
import com.address.ADDRESS.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<List<AddressDto>> savedAddress(@RequestBody AddressRequest addressRequest){
        List<AddressDto> response = addressService.saveAddress(addressRequest);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<List<AddressDto>> updateAddress(
            @RequestBody AddressRequest request
    ) {

        return ResponseEntity.ok(
                addressService.updateAddress(request)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long id
    ) {

        addressService.deleteAddress(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getSingleAddress(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                addressService.getSingleAddress(id)
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> getAllAddress() {

        return ResponseEntity.ok(
                addressService.getAllAddress()
        );
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<AddressDto>> getAddressByEmployeeId(
            @PathVariable Long empId
    ) {

        return ResponseEntity.ok(
                addressService.getAddressByEmployeeId(empId)
        );
    }
}
