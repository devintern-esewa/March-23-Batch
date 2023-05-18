package com.example.springboot_backend.service;


import com.example.springboot_backend.entity.Employee;
import com.example.springboot_backend.exception.ResourceNotfoundException;
import org.springframework.stereotype.Service;
import com.example.springboot_backend.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
    @Override
    public Employee getById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotfoundException("Resource is not available");
        }
        return employee.get();
    }
    @Override
    public Employee update(Employee employee, long id) {
        return employeeRepository.save(employee);
    }
    @Override
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public Employee getByName(String first_name) {
        Optional<Employee> e = employeeRepository.findByfirstName(first_name);
        Employee e1 = new Employee();
        if (e.isPresent()) {
            e1.setFirstName(e.get().getFirstName());
            e1.setLastName(e.get().getLastName());
            e1.setEmail(e.get().getEmail());
        }
        return e1;
    }

}
