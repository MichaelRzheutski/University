package com.solvd.university.service;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> printAllSubjects();
    Student getStudentAllSubjects();
    Student showStudentPerformance();
    String takeExam();
}
