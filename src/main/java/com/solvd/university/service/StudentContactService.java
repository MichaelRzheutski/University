package com.solvd.university.service;

import com.solvd.university.domain.Student;

public interface StudentContactService {
    void createStudentContact(Student student);

    void createStudentContactStax(Student student);

    void createStudentContactJaxb(Student student);

    void createStudentContactJackson(Student student);
}
