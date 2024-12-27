package com.ems.application.controller;

import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import com.ems.application.enums.Role;
import com.ems.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/loginSubmit")
    public String loginSubmit(@ModelAttribute("userLogin") UserLogin userLogin){
        Employee emp = employeeService.loginService(userLogin);
        System.out.println(emp);
        if(emp==null){
            return "redirect:/login";
        }
        return "redirect:/employees/"+emp.getEmployeeId().toString();
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin",userLogin);
        return "login";
    }

    @GetMapping("/employees/{employeeId}")
    public String employeeDashborad(@PathVariable UUID employeeId, Model model){
        Employee emp = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", emp);
        if(emp==null){
            return "redirect:/login";
        }
        System.out.println(emp.getPhone());
        return "dashborad";
    }

}
