package com.solvd.university.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "semester")
@XmlAccessorType(XmlAccessType.FIELD)
public class Semester {
    private Long semesterId;
    private String semesterName;
    private Subject subjectId;

    public Semester(Long semesterId, String semesterName, Subject subjectId) {
        this.semesterId = semesterId;
        this.semesterName = semesterName;
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

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }
}
