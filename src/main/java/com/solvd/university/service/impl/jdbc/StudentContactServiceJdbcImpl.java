package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.parsers.JacksonOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {

    @Override
    public void createStudentContact(Student student, XmlConsoleSelectors consoleSelector) {
        StudentContact studentContact = new StudentContact();
        switch (consoleSelector) {
            case CONSOLE -> studentContact = addContact();
            case STAX -> studentContact = new StaxOperations().readStudentContactFromXml();
            case JAXB -> studentContact = new JaxbOperations().readStudentContactFromJaxb();
            case JACKSON -> studentContact = new JacksonOperations().readStudentContactFromJackson();
        }

        studentContact.setStudentId(student.getStudentId());
        new StudentContactRepositoryJdbcImpl().createStudentContact(studentContact);
    }
}
