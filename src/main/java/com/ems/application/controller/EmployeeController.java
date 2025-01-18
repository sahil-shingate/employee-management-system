package com.ems.application.controller;

import com.ems.application.entity.AttendanceRecord;
import com.ems.application.entity.Employee;
import com.ems.application.entity.UserLogin;
import com.ems.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        return "redirect:/hr/dashboard";
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
        return "redirect:/"+emp.getEmployeeId().toString() + "/profile";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        UserLogin userLogin = new UserLogin();
        model.addAttribute("userLogin",userLogin);
        return "login";
    }

    @GetMapping("/{employeeId}/profile")
    public String employeeDashborad(@PathVariable UUID employeeId, Model model){
        Employee emp = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", emp);
        if(emp==null){
            return "redirect:/login";
        }
        System.out.println(emp.getPhone());
        return "employee_profile";
    }

    @GetMapping("/calendar")
    public String getAttendanceCalendar(Model model) {
        YearMonth currentMonth = YearMonth.now(); // Get the current month
        LocalDate firstDay = currentMonth.atDay(1); // First day of the month
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue(); // Monday = 1, Sunday = 7

        // Prepare the list of days for the current month
        List<Map<String, Object>> days = new ArrayList<>();
        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            LocalDate date = currentMonth.atDay(day);
            boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;

            days.add(Map.of(
                    "date", date,
                    "dayNumber", day,
                    "isWeekend", isWeekend
            ));
        }

        // Add attributes to the model
        model.addAttribute("month", currentMonth);
        model.addAttribute("firstDayOfWeek", firstDayOfWeek); // Pass the first day of the week
        model.addAttribute("days", days);

        return "attendance-calendar";
    }

}
