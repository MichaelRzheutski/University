package com.solvd.university.service.impl.parsers;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;

public interface JaxbStudent {
    Student readStudentFromJaxb();

    StudentContact readStudentContactFromJaxb();
}
