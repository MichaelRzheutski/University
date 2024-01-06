package com.solvd.university.domain;

public class Department {
    private Long departmentId;
    private String departmentName;
    private Building buildingId;
    private Course courseId;
    private Student studentId;
    private Lecturer lecturerId;

    public Department(
            Long departmentId, String departmentName, Building buildingId,
            Course courseId, Student studentId, Lecturer lecturerId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.buildingId = buildingId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.lecturerId = lecturerId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Building getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Building buildingId) {
        this.buildingId = buildingId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Lecturer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Lecturer lecturerId) {
        this.lecturerId = lecturerId;
    }
}
