package com.ems.application.controller;

import com.ems.application.entity.Employee;
import com.ems.application.entity.EmployeeAttendance;
import com.ems.application.enums.AttendanceStatus;
import com.ems.application.service.EmployeeAttendanceService;
import com.ems.application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.UUID;

@Controller
public class AttendanceFormController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeAttendanceService attendanceService;


    @GetMapping("/{employeeId}/attendance")
    public String showAttendanceForm(@PathVariable UUID employeeId, Model model) {
        Employee emp = employeeService.getEmployeeById(employeeId);
        System.out.println(emp.getEmployeeId());
        model.addAttribute("employeeZ", emp);
        if(emp==null){
            return "redirect:/login";
        }
        System.out.println(emp.getPhone());
        return "employee-attendance";
    }

    @PostMapping("/attendance/{employeeId}/set")
    public String setAttendance(
            @PathVariable UUID employeeId,
            @RequestParam LocalDate date,
            @RequestParam AttendanceStatus status,
            @RequestParam(required = false) String remarks,
            Model model) {

        EmployeeAttendance attendance = attendanceService.setAttendance(employeeId, date, status, remarks);
        model.addAttribute("message", "Attendance set successfully for employee " + employeeId);
        model.addAttribute("employeeId",employeeId);
        return "employee-attendance";
    }


    @GetMapping("/api/attendance/monthly-summary")
    public String getMonthlyAttendance(@RequestParam UUID employeeId2, @RequestParam YearMonth yearMonth, Model model) {
        Map<AttendanceStatus, Long> summary = attendanceService.calculateMonthlyAttendance(employeeId2, yearMonth);
        model.addAttribute("summary", summary);
        model.addAttribute("employeeId", employeeId2);
        model.addAttribute("month", yearMonth);
        return "api/attendance/monthly-summary";
    }

}
