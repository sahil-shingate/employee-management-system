package com.ems.application.service;

import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import com.ems.application.enums.Role;
import com.ems.application.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        if(emp.getEmployeeEmail().equals("hr@gmail.com")){
            emp.setRole(Role.HR);
        }
        if(emp.getEmployeeEmail().equals("admin@gmail.com")){
            emp.setRole(Role.ADMIN);
        }
        emp.setEmployeeId(UUID.randomUUID());
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

    @Override
    public Employee loginService(UserLogin userLogin){
       Employee emp = employeeRespository.findEmployeeByEmployeeEmail(userLogin.getEmail());
        System.out.println(emp.getEmployeeEmail() + " ------" + userLogin.getEmail() );
       if(emp==null){
           return null;
       }
       if(emp.getEmployeeEmail().equals(userLogin.getEmail()) && emp.getEmployeePassword().equals(userLogin.getPassword())){
           return emp;
       }
        return null;
    }

    public Employee getEmployeeById(UUID employeeId){
        Employee emp = employeeRespository.findEmployeeByEmployeeId(employeeId);
        if(emp==null){
            return null;
        }
        else{
            return emp;
        }
    }

    @Override
    public Integer deleteEmployee(UUID employeeId) {
        Integer result = employeeRespository.deleteByEmployeeId(employeeId);
        System.out.println(result);
        return result;
    }

}
