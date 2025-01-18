package com.ems.application.entity;

import java.time.LocalDate;
import java.util.UUID;

public class AttendanceRecord {
    private UUID id;
    private LocalDate date;
    private String status; // "Present", "Absent", "Late"

    // Constructor, getters, and setters
    public AttendanceRecord(UUID id, LocalDate date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

