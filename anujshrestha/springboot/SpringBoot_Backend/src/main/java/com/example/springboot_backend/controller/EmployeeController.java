package com.example.springboot_backend.controller;


import com.example.springboot_backend.entity.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot_backend.service.EmployeeService;

import java.util.List;

@RestController

public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //save the employee
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //get all the employee
    @GetMapping("/employees")
    public List<Employee> getAll(Employee employee) {
        return employeeService.getAll();
    }

    //get specific employee
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") long id)  {
        return new ResponseEntity<Employee>(employeeService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.update(employee, id), HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        employeeService.delete(id);
        return new ResponseEntity<String>("Id has be deleted", HttpStatus.OK);
    }
    @GetMapping("/getemployee/{first_name}")
    public ResponseEntity<Employee>getByName(@PathVariable("first_name") String name){
        return new ResponseEntity<Employee>(employeeService.getByName(name),HttpStatus.OK);
    }
//    @GetMapping("/employee/{id}")
//    public ResponseEntity<Employee> getById(Employee employee){
//        return new ResponseEntity<Employee>(employeeService.getById(employee.getId()),HttpStatus.OK);
//    }
}
