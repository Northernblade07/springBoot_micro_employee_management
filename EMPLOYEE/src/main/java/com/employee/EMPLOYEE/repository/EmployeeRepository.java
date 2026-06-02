package com.employee.EMPLOYEE.repository;

import com.employee.EMPLOYEE.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
