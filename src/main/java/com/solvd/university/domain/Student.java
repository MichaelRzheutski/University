package com.solvd.university.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Long studentContactId;
    private Set<Subject> subjects;
    private Double averageScore;

    public Student() {
    }

    public Student(Long studentId, String firstName, String lastName, LocalDate dateOfBirth,
                   Long studentContactId, Set<Subject> subjects, Double averageScore) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.studentContactId = studentContactId;
        this.subjects = subjects;
        this.averageScore = averageScore;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId)
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(dateOfBirth, student.dateOfBirth)
                && Objects.equals(studentContactId, student.studentContactId)
                && Objects.equals(subjects, student.subjects)
                && Objects.equals(averageScore, student.averageScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, dateOfBirth,
                studentContactId, subjects, averageScore);
    }
}
