package com.employee.EMPLOYEE.client;

import com.employee.EMPLOYEE.model.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
//there are two ways you can set up feign client by using name from eureka server like ADDRESS or
// you can give a unique name but specify the url for address service in application properties
//like address.service.url (check application.properties) and then use the url in @FeignClient(url = "${address.service.url}")
//like in employee service
@FeignClient(name = "ADDRESS")
public interface AddressClient {

    @GetMapping("/api/v1/address/employee/{empId}")
    List<AddressDto> getAddressByEmployeeId(@PathVariable Long empId);
}
