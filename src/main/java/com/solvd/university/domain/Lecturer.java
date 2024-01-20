package com.solvd.university.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lecturer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lecturer {
    private Long lecturerId;
    private String firstName;
    private String lastName;
    private Subject subjectId;
    private LecturerContact lecturerContact;
    private Department department;

    public Lecturer() {
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public LecturerContact getLecturerContact() {
        return lecturerContact;
    }

    public void setLecturerContact(LecturerContact lecturerContact) {
        this.lecturerContact = lecturerContact;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
