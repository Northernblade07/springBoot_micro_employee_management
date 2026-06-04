package com.employee.EMPLOYEE.service.impl;

import com.employee.EMPLOYEE.exception.BadRequestException;
import com.employee.EMPLOYEE.exception.ResourceNotFound;
import com.employee.EMPLOYEE.model.dto.EmployeeDto;
import com.employee.EMPLOYEE.model.entity.Employee;
import com.employee.EMPLOYEE.repository.EmployeeRepository;
import com.employee.EMPLOYEE.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
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
        return modelMapper.map(employee  , EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        if(id == null){
            throw new BadRequestException("please provide proper id , there can be mismatch");
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFound("no user exists with this id:"+id));
//        Employee entity = modelMapper.map(employeeDto , Employee.class);
//        Employee updatedEmployee = employeeRepository.save(entity);
//
//        return modelMapper.map(updatedEmployee , EmployeeDto.class);

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
        return employees.stream().map( e -> modelMapper.map(e , EmployeeDto.class)).toList();
    }


}
