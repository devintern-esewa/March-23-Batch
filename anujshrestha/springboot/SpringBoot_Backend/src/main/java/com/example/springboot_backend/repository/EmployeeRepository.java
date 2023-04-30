package com.example.springboot_backend.repository;


import com.example.springboot_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByfirstName(String firstName);
}
