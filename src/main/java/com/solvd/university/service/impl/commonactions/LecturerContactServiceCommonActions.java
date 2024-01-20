package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.LecturerContact;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class LecturerContactServiceCommonActions {
    protected LecturerContact addContact() {
        Scanner scanner = new Scanner(System.in);
        LecturerContact lecturerContact = new LecturerContact();

        MY_LOGGER.info(ANSI_GREEN + "Введите номер телефона:" + ANSI_RESET);
        if (scanner.hasNext()) {
            lecturerContact.setPhone(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите Email:" + ANSI_RESET);
        if (scanner.hasNext()) {
            lecturerContact.setEmail(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return lecturerContact;
    }
}
