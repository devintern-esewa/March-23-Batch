package com.bismdhr.bankmgmtsystem.service;

import com.bismdhr.bankmgmtsystem.dto.EmployeeUpdateDto;
import com.bismdhr.bankmgmtsystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee findById(int id);

    Employee findByFullName(String fullName);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(EmployeeUpdateDto employee);

    String deleteEmployee(int id);
}
