package com.solvd.university.domain;

public class Semester {
    private Long semesterId;
    private String semesterName;
    private Course courseId;
    private Subject subjectId;

    public Semester(Long semesterId, String semesterName, Course courseId, Subject subjectId) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
        this.courseId = courseId;
        this.subjectId = subjectId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }
}
