package com.bismdhr.bankmgmtsystem.repository;

import com.bismdhr.bankmgmtsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
