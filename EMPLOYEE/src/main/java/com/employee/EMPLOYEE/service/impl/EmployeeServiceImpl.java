package com.employee.EMPLOYEE.service.impl;

import com.employee.EMPLOYEE.client.AddressClient;
import com.employee.EMPLOYEE.exception.BadRequestException;
import com.employee.EMPLOYEE.exception.ResourceNotFound;
import com.employee.EMPLOYEE.model.dto.AddressDto;
import com.employee.EMPLOYEE.model.dto.EmployeeDto;
import com.employee.EMPLOYEE.model.entity.Employee;
import com.employee.EMPLOYEE.repository.EmployeeRepository;
import com.employee.EMPLOYEE.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final AddressClient addressClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            throw new BadRequestException("employee already exits");
        }

       Employee employee =  modelMapper.map(employeeDto , Employee.class);
       Employee savedEmployee =  employeeRepository.save(employee);

       return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (id == null){
            return;
        }

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("no employee found with id"+id));
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getSingleEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("No employee found"));
        List<AddressDto> address = new ArrayList<>();
       try {
           address = addressClient.getAddressByEmployeeId(id);
       } catch (Exception e){
           log.info("no address for this employe with id:"+id);
       }
       EmployeeDto response =  modelMapper.map(employee  , EmployeeDto.class);
       response.setAddress(address);
       return response;
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        if(id == null){
            throw new BadRequestException("please provide proper id , there can be mismatch");
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("no user exists with this id:"+id));

        employee.setName(employeeDto.getName());
        employee.setEmpCode(employeeDto.getEmpCode());
        employee.setCompanyName(employeeDto.getCompanyName());
        employee.setEmpEmail(employeeDto.getEmpEmail());

        employeeRepository.save(employee);
        return modelMapper.map(employee , EmployeeDto.class);

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            throw new ResourceNotFound("no employee found");
        }

        List<EmployeeDto> employeeDtos=  employees.stream().map( e -> modelMapper.map(e , EmployeeDto.class)).toList();

        List<EmployeeDto> response = new ArrayList<>();
        for (EmployeeDto dto : employeeDtos){
            try{
                List<AddressDto> address = addressClient.getAddressByEmployeeId(dto.getId());
                dto.setAddress(address);
            } catch (Exception e){
                log.info("no address for employe with id:"+dto.getId());
            }
            response.add(dto);
        }

        return response;

    }


}
