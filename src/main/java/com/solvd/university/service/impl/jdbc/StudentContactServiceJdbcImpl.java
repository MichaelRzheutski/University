package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        StudentContact studentContact = addContact();
        new StudentContactRepositoryJdbcImpl().create(studentContact);

        MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: " + ANSI_YELLOW
                + studentContact.getPhone() + " " + studentContact.getEmail() + ANSI_RESET + "\n");
    }
}
