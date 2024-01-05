package com.solvd.university.service;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentService {
    void enrollStudent();

    void findStudent();

    void editStudentInfo();

    List<Student> printAllSubjects();

    Student getStudentAllSubjects();

    Student showStudentPerformance();

    String takeExam();

    void expelStudentById();

    void printNumberOfEntries();
}
