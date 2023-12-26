package com.solvd.university.domain;

import java.util.Date;
import java.util.List;

public class Student {
    private Long studentId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private StudentContact studentContactId;
    private Course course;
    private Semester semester;
    private List<Subject> subjects;

    public Student() {
    }

    public Student(
            Long studentId, String firstName, String lastName, Date dateOfBirth,
            StudentContact studentContactId, Course course, Semester semester, List<Subject> subjects) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.studentContactId = studentContactId;
        this.course = course;
        this.semester = semester;
        this.subjects = subjects;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StudentContact getStudentContactId() {
        return studentContactId;
    }

    public void setStudentContactId(StudentContact studentContactId) {
        this.studentContactId = studentContactId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
