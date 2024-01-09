package com.solvd.university.service;

import com.solvd.university.domain.Student;

public interface StudentService {
    void printFullStudentInfo();

    void enrollStudent();

    void enrollStudentStax();

    void enrollStudentJaxb();

    Student findStudent();

    void editStudentInfo();

    void expelStudentById();

    void printNumberOfEntries();
}
