package com.ems.application.repository;

import com.ems.application.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Transactional
@Repository
public interface EmployeeRespository extends JpaRepository<Employee, String> {
    Employee findEmployeeByEmployeeEmail(String employeeEmail);
    Employee findEmployeeByEmployeeId(UUID employeeId);
    Integer deleteByEmployeeId(UUID employeeId);
}
