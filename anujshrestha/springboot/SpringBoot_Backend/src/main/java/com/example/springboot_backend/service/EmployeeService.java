package com.example.springboot_backend.service;



import com.example.springboot_backend.entity.Employee;
import com.example.springboot_backend.exception.ResourceNotfoundException;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAll();
    Employee getById(long id) throws ResourceNotfoundException;
    Employee update(Employee employee,long id);
    void delete(long id);
    Employee getByName(String first_name);
}
