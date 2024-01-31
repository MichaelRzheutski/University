package com.solvd.university.service;

import com.solvd.university.domain.Student;
import com.solvd.university.util.menus.enums.ParserSelectors;

public interface StudentService {
    void printFullStudentInfo();

    void enrollStudent(ParserSelectors xmlConsoleSelector);

    Student findStudent();

    void editStudentInfo();

    void expelStudentById();

    void printNumberOfEntries();
}
