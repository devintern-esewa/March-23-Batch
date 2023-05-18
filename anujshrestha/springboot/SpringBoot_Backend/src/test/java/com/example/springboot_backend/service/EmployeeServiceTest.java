package com.example.springboot_backend.service;


import com.example.springboot_backend.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.springboot_backend.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void getByName_whenValidEmployeeName_thenEmployeeShouldBeFound() {

        String name = "don";
        Employee employee = Employee.builder()
                .firstName("don")
                .email("don@gmail.com")
                .lastName("hero")
                .build();

        Optional<Employee> optionalEmployee = Optional.of(employee);

        when(employeeRepository.findByfirstName(name)).thenReturn(optionalEmployee);

        Employee employeeResp = employeeService.getByName(name);
        assertNotNull(employeeResp);

        assertEquals(employee.getFirstName(), employeeResp.getFirstName());
    }

    @Test
    public void saveEmployee_whenSaveEmployee_thenEmployeeNoIncrease() {
        //arrange
        Employee employee = new Employee(1L, "Anuj", "Shrestha", "Hero@gmail.com");
        //act
        when( employeeRepository.save(employee)).thenReturn(employee);
        //assert
        Assertions.assertThat(employeeService.saveEmployee(employee).getId()).isGreaterThan(0L);
    }

    @Test
    public void getAll_whenGetAll_thenCountShouldBeEqual() {
        // Arrange
        Employee employees1 = new Employee(1L, "Anuj", "Shrestha", "anuj@gmail.com");
        Employee employees2 = new Employee(2L, "Hari", "Tandukar", "Hari@gmail.com");
        List<Employee> employees = Arrays.asList(employees1, employees2);
        when(employeeRepository.findAll()).thenReturn(employees);
        // Act
        List<Employee> result = employeeService.getAll();
        // Assert
        assertEquals(employees.size(), result.size());
    }
    @Test
    public void getById_whenGetById_thenIdShouldBeSame(){
        //Arrange
        Employee employee1= new Employee(1L, "Anuj","Shrestha","anuj@gmail.com");
        //Act
        when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));
        Employee emp= employeeService.getById(employee1.getId());
        //Assert
        assertSame(employee1.getId(),emp.getId());
    }
    @Test
    public void update_whenUpdate_thenNameShouldBeChanged(){
        //Arrange
        Employee employee1= new Employee(1L, "Anuj","Shrestha","anuj@gmail.com");
        String changed=employee1.getFirstName();
        employee1.setFirstName("Hari");
        //Act
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        Employee employeeData= employeeService.update(employee1,employee1.getId());
        //assert
        assertNotSame(changed,employeeData.getFirstName());
//        assertSame(changed,employeeService.update(employee1,employee1.getId()).getFirstName());

    }

}