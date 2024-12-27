package com.ems.application.service;

import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployee(String employeeName);

    Employee addEmployee(Employee emp);

    Employee removeEmployee(Employee emp);

    Employee updateEmployee(Employee emp);

    void deleteEmployees();

    Employee loginService(UserLogin userLogin);

    Employee getEmployeeById(UUID employeeId);

    Integer deleteEmployee(UUID employeeId);

}
