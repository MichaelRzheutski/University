package com.solvd.university.service;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentService {
    public List<Student> printAllSubjects();
    public Student getStudentSubjects();
    public Student showStudentPerformance();
    public String takeExam();
}
