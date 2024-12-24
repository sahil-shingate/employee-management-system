package com.ems.application.repository;

import com.ems.application.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, UUID> {
}
