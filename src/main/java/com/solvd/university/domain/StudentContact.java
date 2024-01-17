package com.solvd.university.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "studentContact")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentContact {
    private Long studentContactId;
    private String phone;
    private String email;
    private Long studentId;

    public StudentContact() {
    }

    public StudentContact(Long studentContactId, String phone, String email, Long studentId) {
        this.studentContactId = studentContactId;
        this.phone = phone;
        this.email = email;
        this.studentId = studentId;
    }

    public Long getStudentContactId() {
        return studentContactId;
    }

    public void setStudentContactId(Long studentContactId) {
        this.studentContactId = studentContactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
