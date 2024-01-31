package com.solvd.university.service.impl.proxy;

import com.solvd.university.domain.Student;

import java.util.List;

public interface AllSubjectsProxy {
    List<Student> getStudentsWithSubjects();

    void printAllSubjects();
}
