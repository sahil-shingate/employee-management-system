package com.ems.application.service;


import com.ems.application.entity.EmployeeAttendance;
import com.ems.application.repository.EmployeeAttendanceRepository;
import com.ems.application.enums.AttendanceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeAttendanceService {

    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;

    /**
     * Set or update attendance for an employee on a specific date.
     *
     * @param employeeId the ID of the employee
     * @param date       the date of attendance
     * @param status     the attendance status
     * @param remarks    optional remarks
     * @return the saved EmployeeAttendance object
     */
    public EmployeeAttendance setAttendance(UUID employeeId, LocalDate date, AttendanceStatus status, String remarks) {
        Optional<EmployeeAttendance> existingAttendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

        EmployeeAttendance attendance;
        if (existingAttendance.isPresent()) {
            attendance = existingAttendance.get();
            attendance.setStatus(status);
            attendance.setRemarks(remarks);
        } else {
            attendance = new EmployeeAttendance(employeeId, date, status, remarks);
        }

        return attendanceRepository.save(attendance);
    }

    /**
     * Get attendance for a specific employee on a specific date.
     *
     * @param employeeId the ID of the employee
     * @param date       the date of attendance
     * @return the EmployeeAttendance object if found
     */
    public Optional<EmployeeAttendance> getAttendance(UUID employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }

    /**
     * Get all attendance records for an employee.
     *
     * @param employeeId the ID of the employee
     * @return a list of attendance records
     */
    public List<EmployeeAttendance> getAttendanceByEmployee(UUID employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    /**
     * Update attendance for a specific record.
     *
     * @param attendanceId the ID of the attendance record
     * @param status       the new attendance status
     * @param remarks      optional remarks
     * @return the updated EmployeeAttendance object
     */
    public EmployeeAttendance updateAttendance(Long attendanceId, AttendanceStatus status, String remarks) {
        EmployeeAttendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setStatus(status);
        attendance.setRemarks(remarks);
        return attendanceRepository.save(attendance);
    }

    /**
     * Calculate monthly attendance for an employee.
     *
     * @param employeeId the ID of the employee
     * @param yearMonth  the month (e.g., 2025-01)
     * @return a summary map of attendance status counts
     */
    public Map<AttendanceStatus, Long> calculateMonthlyAttendance(UUID employeeId, YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Fetch attendance records for the month
        List<EmployeeAttendance> records = attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);

        // Group by attendance status and count each
        return records.stream()
                .collect(Collectors.groupingBy( EmployeeAttendance::getStatus, Collectors.counting()));
    }
}

