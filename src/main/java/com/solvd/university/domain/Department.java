package com.solvd.university.domain;

public class Department {
    private Long departmentId;
    private String departmentName;
    private Integer course;

    public Department() {
    }

    public Department(
            Long departmentId, String departmentName, Integer course) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.course = course;
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

}
