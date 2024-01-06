package com.solvd.university.domain;

public class Classroom {
    private Long classroomId;
    private String classroomName;

    public Classroom(Long classroomId, String classroomName) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
