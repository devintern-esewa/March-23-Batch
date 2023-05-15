package com.bismdhr.bankmgmtsystem.service.impl;

import com.bismdhr.bankmgmtsystem.dto.EmployeeUpdateDto;
import com.bismdhr.bankmgmtsystem.exception.NotFoundException;
import com.bismdhr.bankmgmtsystem.model.Employee;
import com.bismdhr.bankmgmtsystem.repository.EmployeeRepository;
import com.bismdhr.bankmgmtsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> optionalEmp = employeeRepository.findById(id);
        return optionalEmp.orElseThrow(() -> new NotFoundException("Employee not found for id :"+id));
    }
    @Override
    public Employee findByFullName(String fullName) {
        return null;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateDto updateDto) {
        Employee employee = findById(updateDto.getId());
        employee.setFullName(updateDto.getFullName());
        employee.setEmail(updateDto.getEmail());
        employee.setAddress(updateDto.getAddress());
        employee.setPosition(updateDto.getPosition());
        return employeeRepository.save(employee);
    }

    @Override
    public String deleteEmployee(int id) {
        findById(id);
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }
}
