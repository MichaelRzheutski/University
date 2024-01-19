package com.solvd.university.service;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;

public interface JaxBService {
    Student readStudentFromJaxb();

    StudentContact readStudentContactFromJaxb();
}
