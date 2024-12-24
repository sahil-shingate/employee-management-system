package com.ems.application.controller;

import com.ems.application.entity.Employee;
import com.ems.application.enums.Role;
import com.ems.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employees" , employeeList );
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee" , employee );
        return "add_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployees")
    public String deleteEmployees(){
        employeeService.deleteEmployees();
        return "redirect:/employees";
    }

    @GetMapping("/")
    public String redirectHome(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }



}
