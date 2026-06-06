package com.employee.EMPLOYEE.client;

import com.employee.EMPLOYEE.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "addressClient" , url = "${address.service.url}")
public interface AddressClient {

    @GetMapping("/employee/{empId}")
    List<AddressDto> getAddressByEmployeeId(@PathVariable Long empId);
}
