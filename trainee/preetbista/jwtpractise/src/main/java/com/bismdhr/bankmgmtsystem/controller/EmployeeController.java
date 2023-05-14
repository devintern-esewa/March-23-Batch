package com.bismdhr.bankmgmtsystem.controller;

import com.bismdhr.bankmgmtsystem.dto.EmployeeUpdateDto;
import com.bismdhr.bankmgmtsystem.model.Employee;
import com.bismdhr.bankmgmtsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping
    public Employee addEmployee( @RequestBody @Valid Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee (@RequestBody @Valid EmployeeUpdateDto employee){
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
        return employeeService.deleteEmployee(id);
    }
}
