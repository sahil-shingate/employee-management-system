package com.ems.application.controller;

import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import com.ems.application.enums.Role;
import com.ems.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hr")
public class HRController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String loginPage(Model model){
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin",userLogin);
        return "hrLogin";
    }

    @PostMapping("/loginSubmit")
    public String loginSubmit(@ModelAttribute("userLogin") UserLogin userLogin){
        Employee emp = employeeService.loginService(userLogin);
        System.out.println(emp);
        if(emp==null){
            return "redirect:/hr/login";
        }
        if(emp.getRole().equals(Role.HR)){
            return "redirect:/hr/dashboard";
        }
        return "redirect:/hr/login";
    }

    @GetMapping("/dashboard")
    public String hrDashboard(Model model){
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("employees" , employeeList );
        return "hr_dashboard";
    }

    @GetMapping("/{employeeId}/edit1")
    public String editEmployee(@PathVariable UUID employeeId, Model model){
        Employee emp = employeeService.getEmployeeById(employeeId);
        System.out.println(emp.getEmployeeId());
        model.addAttribute("employee", emp);
        return "edit_employee";
    }

    @GetMapping("/{employeeId}/delete")
    public String deleteEmployee(@PathVariable UUID employeeId, Model model){
        Employee emp = employeeService.getEmployeeById(employeeId);
        employeeService.deleteEmployee(employeeId);
        model.addAttribute("employee", emp);
        return "redirect:/hr/dashboard";
    }


    @GetMapping("/{employeeId}/edit")
    public String addEdit(@PathVariable UUID employeeId, Model model){
        Employee emp = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", emp);
        return "addedit";
    }

    @PostMapping("/{employeeId}/update")
    public String updateEmployee(@PathVariable UUID employeeId, @ModelAttribute("employee") Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/hr/dashboard";

    }

    @GetMapping({"/{employeeId}/profile"})
    public String employeeProfile(@PathVariable UUID employeeId, Model model) {
        Employee emp = this.employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", emp);
        return "employee_profile";
    }
}
