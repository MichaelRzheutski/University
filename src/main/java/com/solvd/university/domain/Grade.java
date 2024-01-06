package com.solvd.university.domain;

public class Grade {
    private Long gradeId;
    private Integer grade;
    private Subject subjectId;

    public Grade(Long gradeId, Integer grade, Subject subjectId) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.subjectId = subjectId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }
}
