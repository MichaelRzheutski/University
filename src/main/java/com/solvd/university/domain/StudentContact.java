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
    private String socialNetwork;

    public StudentContact() {
    }

    public StudentContact(Long studentContactId, String phone, String email, String socialNetwork) {
        this.studentContactId = studentContactId;
        this.phone = phone;
        this.email = email;
        this.socialNetwork = socialNetwork;
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

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }
}
