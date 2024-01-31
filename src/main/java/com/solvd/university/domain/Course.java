package com.solvd.university.domain;

public class Course {
    private Long courseId;
    private String courseName;
    private Semester semesterId;

    public Course(Long courseId, String courseName, Semester semesterId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.semesterId = semesterId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
    }
}
