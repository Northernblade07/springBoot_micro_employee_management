package com.employee.EMPLOYEE.service.impl;

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
            throw new RuntimeException("employee already exits");
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

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("no employee found"));
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getSingleEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("No employee found"));
        return modelMapper.map(employee  , EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        if(id == null){
            throw new RuntimeException("please provide proper id , there can be mismatch");
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("no user exists with this is id"));
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

        return employees.stream().map( e -> modelMapper.map(e , EmployeeDto.class)).toList();
    }


}
