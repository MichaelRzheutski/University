package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        StudentContact studentContact = addContact();
        new StudentContactRepositoryJdbcImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }

    @Override
    public void createStudentContactStax() {
        StudentContact studentContact = new StaxOperations().readStudentContactFromXml();
        new StudentContactRepositoryJdbcImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }

    @Override
    public void createStudentContactJaxb() {
        StudentContact studentContact = new JaxbOperations().readStudentContactFromJaxb();
        new StudentContactRepositoryJdbcImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }
}
