package com.solvd.university.domain;

import com.solvd.university.util.parsers.XmlDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
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
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private LocalDate dateOfBirth;
    private Long studentContactId;
    private Set<Subject> subjects;
    private Double averageScore;
    private Department department;

    public Student() {
    }

    public Student(Long studentId, String firstName, String lastName, LocalDate dateOfBirth,
                   Long studentContactId, Set<Subject> subjects, Double averageScore, Department department) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.studentContactId = studentContactId;
        this.subjects = subjects;
        this.averageScore = averageScore;
        this.department = department;
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

    public Long getStudentContactId() {
        return studentContactId;
    }

    public void setStudentContactId(Long studentContactId) {
        this.studentContactId = studentContactId;
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
        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(dateOfBirth, student.dateOfBirth) && Objects.equals(studentContactId, student.studentContactId) && Objects.equals(subjects, student.subjects) && Objects.equals(averageScore, student.averageScore) && Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, dateOfBirth, studentContactId, subjects, averageScore, department);
    }
}
