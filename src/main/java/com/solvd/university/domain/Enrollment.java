package com.solvd.university.domain;

import java.util.Date;

public class Enrollment {
    private Long enrollmentId;
    private Date enrollmentDate;
    private Grade grade;
    private Course courseId;

    public Enrollment(Long enrollmentId, Date enrollmentDate, Grade grade, Course courseId) {
        this.enrollmentId = enrollmentId;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
        this.courseId = courseId;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }
}
