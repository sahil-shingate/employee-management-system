package com.ems.application.service;

import com.ems.application.entity.Employee;
import com.ems.application.enums.Role;
import com.ems.application.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRespository employeeRespository;

    public EmployeeServiceImpl(EmployeeRespository employeeRespository){
        super();
        this.employeeRespository=employeeRespository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRespository.findAll();
    }

    @Override
    public Employee getEmployee(String employeeName) {
        return null;
    }

    @Override
    public Employee addEmployee(Employee emp) {
        emp.setRole(Role.EMPLOYEE);
        employeeRespository.save(emp);
        return emp;
    }

    @Override
    public Employee removeEmployee(Employee emp) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee emp) {
        return null;
    }

    @Override
    public void deleteEmployees(){
        employeeRespository.deleteAll();
    }
}
