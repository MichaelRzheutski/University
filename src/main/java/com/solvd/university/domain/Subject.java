package com.solvd.university.domain;

import static com.solvd.university.util.ConsoleColors.*;

public class Subject {
    private Long subjectId;
    private String subjectName;
    private Integer grade;

    public Subject() {

    }

    public Subject(Long subjectId, String subjectName, Integer grade) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.grade = grade;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return ANSI_YELLOW + subjectName + ANSI_RESET;
    }
}
