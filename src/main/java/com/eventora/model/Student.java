package com.eventora.model;

import java.sql.Timestamp;

public class Student {
    private int studentId;
    private String fullName, email, password, rollNumber, department, phone;
    private Timestamp createdAt;

    public Student() {}
    public Student(String fullName, String email, String password,
                   String rollNumber, String department, String phone) {
        this.fullName=fullName; this.email=email; this.password=password;
        this.rollNumber=rollNumber; this.department=department; this.phone=phone;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int v) { studentId=v; }
    public String getFullName() { return fullName; }
    public void setFullName(String v) { fullName=v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email=v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { password=v; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String v) { rollNumber=v; }
    public String getDepartment() { return department; }
    public void setDepartment(String v) { department=v; }
    public String getPhone() { return phone; }
    public void setPhone(String v) { phone=v; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp v) { createdAt=v; }
}
