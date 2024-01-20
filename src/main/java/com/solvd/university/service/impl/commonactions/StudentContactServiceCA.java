package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.StudentContact;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentContactServiceCA {
    protected StudentContact addContact() {
        Scanner scanner = new Scanner(System.in);
        StudentContact studentContact = new StudentContact();

        MY_LOGGER.info(ANSI_GREEN + "Введите номер телефона:" + ANSI_RESET);
        if (scanner.hasNext()) {
            studentContact.setPhone(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите Email:" + ANSI_RESET);
        if (scanner.hasNext()) {
            studentContact.setEmail(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return studentContact;
    }
}
