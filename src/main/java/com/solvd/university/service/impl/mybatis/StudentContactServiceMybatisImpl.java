package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.parsers.JacksonOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

public class StudentContactServiceMybatisImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact(Student student) {
        StudentContact studentContact = addContact();
        new StudentContactRepositoryMybatisImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactStax(Student student) {
        StudentContact studentContact = new StaxOperations().readStudentContactFromXml();
        new StudentContactRepositoryMybatisImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactJaxb(Student student) {
        StudentContact studentContact = new JaxbOperations().readStudentContactFromJaxb();
        new StudentContactRepositoryMybatisImpl().createStudentContact(student, studentContact);
    }

    @Override
    public void createStudentContactJackson(Student student) {
        StudentContact studentContact = new JacksonOperations().readStudentContactFromJackson();
        new StudentContactRepositoryMybatisImpl().createStudentContact(student, studentContact);
    }
}
