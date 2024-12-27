package com.ems.application.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "employee_receipt")
public class EmployeeRecepit {

    @Id
    @Column(name = "receipt_id")
    private UUID receiptId;

    @Column(name = "email")
    private String employeeEmail;

    @Column(name = "employeeId")
    private UUID employeeId;

    @Column(name = "pay_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    @Column(name = "base_salary")
    private Float baseSalary;

    @Column(name = "bonus_paid")
    private Float bonus_paid;

    @Column(name = "deduction")
    private Float deduction;

    @Column(name = "net_pay")
    private Float netPay;

    public EmployeeRecepit(){

    }

    public EmployeeRecepit(UUID receiptId, String employeeEmail, UUID employeeId, Date payDate, Float baseSalary, Float bonus_paid, Float deduction, Float netPay) {
        this.receiptId = receiptId;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.payDate = payDate;
        this.baseSalary = baseSalary;
        this.bonus_paid = bonus_paid;
        this.deduction = deduction;
        this.netPay = netPay;
    }

    public UUID getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(UUID receiptId) {
        this.receiptId = receiptId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Float getBonus_paid() {
        return bonus_paid;
    }

    public void setBonus_paid(Float bonus_paid) {
        this.bonus_paid = bonus_paid;
    }

    public Float getDeduction() {
        return deduction;
    }

    public void setDeduction(Float deduction) {
        this.deduction = deduction;
    }

    public Float getNetPay() {
        return netPay;
    }

    public void setNetPay(Float netPay) {
        this.netPay = netPay;
    }
}
