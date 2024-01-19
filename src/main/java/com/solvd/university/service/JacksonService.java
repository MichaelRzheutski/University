package com.solvd.university.service;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;

public interface JacksonService {
    Student readStudentFromJackson();

    StudentContact readStudentContactFromJackson();
}
