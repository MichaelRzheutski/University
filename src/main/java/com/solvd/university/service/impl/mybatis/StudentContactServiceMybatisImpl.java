package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentContactServiceMybatisImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        StudentContact studentContact = addContact();
        new StudentContactRepositoryMybatisImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }

    @Override
    public void createStudentContactStax() {
        StudentContact studentContact = new StaxOperations().readStudentContactFromXml();
        new StudentContactRepositoryMybatisImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }

    @Override
    public void createStudentContactJaxb() {
        StudentContact studentContact = new JaxbOperations().readStudentContactFromJaxb();
        new StudentContactRepositoryMybatisImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: \n" + ANSI_YELLOW
                + studentContact.getPhone() + " \n" + studentContact.getEmail() + ANSI_RESET + "\n");
    }
}
