package com.example.springbootjpademo.service;

import com.example.springbootjpademo.repository.EmployeeRepository;
import com.example.springbootjpademo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "User added Successfully";
    }

    @Override
    public String deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public String updateEmployeeById(int id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not found employee"));
        employee1.setEmp_name(employee.getEmp_name());
        employee1.setEmp_address(employee.getEmp_address());
        employeeRepository.save(employee1);
        return "User Update successfully";
    }
}
