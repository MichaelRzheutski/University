package com.solvd.university.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lecturerContact")
@XmlAccessorType(XmlAccessType.FIELD)
public class LecturerContact {
    private Long lecturerContactId;
    private String phone;
    private String email;

    public LecturerContact(Long lecturerContactId, String phone, String email) {
        this.lecturerContactId = lecturerContactId;
        this.phone = phone;
        this.email = email;
    }

    public Long getLecturerContactId() {
        return lecturerContactId;
    }

    public void setLecturerContactId(Long lecturerContactId) {
        this.lecturerContactId = lecturerContactId;
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
}
