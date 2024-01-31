package com.solvd.university.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.university.util.parsers.JsonDateAdapter;
import com.solvd.university.util.parsers.XmlDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    @JsonDeserialize(using = JsonDateAdapter.class)
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private LocalDate dateOfBirth;
    private StudentContact studentContact;
    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private Set<Subject> subjects;
    private Double averageScore;
    private Department department;

    public Student() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StudentContact getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(StudentContact studentContact) {
        this.studentContact = studentContact;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId)
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(dateOfBirth, student.dateOfBirth)
                && Objects.equals(studentContact, student.studentContact)
                && Objects.equals(subjects, student.subjects)
                && Objects.equals(averageScore, student.averageScore)
                && Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName,
                dateOfBirth, studentContact, subjects, averageScore, department);
    }
}
