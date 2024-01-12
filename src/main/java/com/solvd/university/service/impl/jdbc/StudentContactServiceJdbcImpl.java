package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.parsers.JacksonOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact(Student student) {
        StudentContact studentContact = addContact();
        new StudentContactRepositoryJdbcImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactStax(Student student) {
        StudentContact studentContact = new StaxOperations().readStudentContactFromXml();
        new StudentContactRepositoryJdbcImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactJaxb(Student student) {
        StudentContact studentContact = new JaxbOperations().readStudentContactFromJaxb();
        new StudentContactRepositoryJdbcImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactJackson(Student student) {
        StudentContact studentContact = new JacksonOperations().readStudentContactFromJackson();
        new StudentContactRepositoryJdbcImpl().createStudentContact(student, studentContact);
    }
}
