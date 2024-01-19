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
    private Department departmentId;
    private Subject subjectId;
    private LecturerContact lecturerContactId;

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

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public LecturerContact getLecturerContactId() {
        return lecturerContactId;
    }

    public void setLecturerContactId(LecturerContact lecturerContactId) {
        this.lecturerContactId = lecturerContactId;
    }
}
