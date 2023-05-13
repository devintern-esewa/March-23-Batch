package com.example.springbootjpademo.service;

import com.example.springbootjpademo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(int id);

    String addEmployee(Employee employee);

    String deleteEmployeeById(int id);

    String updateEmployeeById(int id, Employee employee);
}
