package com.solvd.university.service;

import com.solvd.university.domain.Student;

public interface StudentService {
    void printFullStudentInfo();

    void enrollStudent();

    Student findStudent();

    void editStudentInfo();

    void expelStudentById();

    void printNumberOfEntries();
}
