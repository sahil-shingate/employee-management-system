package com.ems.application.repository;

import com.ems.application.entity.EmployeeAttendance;
import com.ems.application.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    Optional<EmployeeAttendance> findByEmployeeIdAndDate(UUID employeeId, LocalDate date);

    List<EmployeeAttendance> findByEmployeeId(UUID employeeId);

    List<EmployeeAttendance> findByEmployeeIdAndStatus(UUID employeeId, AttendanceStatus status);

    List<EmployeeAttendance> findByEmployeeIdAndDateBetween(UUID employeeId, LocalDate startDate, LocalDate endDate);
}

