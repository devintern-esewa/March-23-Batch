package com.example.springbootjpademo.controller;

import com.example.springbootjpademo.entity.Employee;
import com.example.springbootjpademo.exception.ResourceNotFoundException;
import com.example.springbootjpademo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @PostMapping(value = "/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return employee;
    }

    @DeleteMapping(value = "/delete/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
        return employeeService.getAllEmployee();
    }

    @PutMapping(value = "/update/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employeeService.updateEmployeeById(id, employee);
        return employee + " Update Successfully";
    }
}

