package com.ems.application.service;

import com.ems.application.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployee(String employeeName);

    Employee addEmployee(Employee emp);

    Employee removeEmployee(Employee emp);

    Employee updateEmployee(Employee emp);

    void deleteEmployees();
}
