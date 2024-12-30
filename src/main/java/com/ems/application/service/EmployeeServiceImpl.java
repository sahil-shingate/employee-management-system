package com.ems.application.service;

import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import com.ems.application.enums.Role;
import com.ems.application.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRespository employeeRespository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRespository employeeRespository){
        super();
        this.employeeRespository=employeeRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> opt = Optional.ofNullable(employeeRespository.findEmployeeByEmployeeEmail(email));
        User user = null;
        if(opt.isEmpty()){
            throw new UsernameNotFoundException("User with mail:-" + email + " not found");
        }else{
            Employee emp = opt.get();
            String role = emp.getRole().toString();
            System.out.println("get role" + role);
            Set<GrantedAuthority> ga = new HashSet<>();
            ga.add(new SimpleGrantedAuthority(role));
            user = new User(email, emp.getEmployeePassword(), ga);
        }
        return user;
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
        String password = emp.getEmployeePassword();
        String encodedPassword = passwordEncoder.encode(password);
        emp.setEmployeePassword(encodedPassword);
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
       if(emp.getEmployeeEmail().equals(userLogin.getEmail()) && passwordEncoder.matches(userLogin.getPassword(),emp.getEmployeePassword())){
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
