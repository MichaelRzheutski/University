package com.solvd.university.util.menus;

import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.StudentMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public final class StudentMenu {
    public void showStudentMenu(Scanner scanner) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню студента: " + ANSI_RESET);
                MY_LOGGER.info("[1]. " + StudentMenuItems.UNIVERSITY_DEPARTMENTS);
                MY_LOGGER.info("[2]. " + StudentMenuItems.UNIVERSITY_ENROLLMENT_RESULTS);
                MY_LOGGER.info("[3]. " + StudentMenuItems.UNIVERSITY_STUDENTS);
                MY_LOGGER.info("[4]. " + StudentMenuItems.UNIVERSITY_LECTURERS);
                MY_LOGGER.info("[5]. " + StudentMenuItems.UNIVERSITY_SUBJECT_LIST);
                MY_LOGGER.info("[6]. " + GeneralMenuItems.AUTOSERVICE_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.AUTOSERVICE_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 3 -> isExit = true;
                        default -> MY_LOGGER.info(
                                String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                        ANSI_RED, ANSI_RESET)
                        );
                    }
                } else {
                    throw new NotNumberException(
                            "вместо числа введена строка "
                                    + ANSI_YELLOW + scanner.next() + ANSI_RESET);
                }
            }
        } catch (NotNumberException e) {
            MY_LOGGER.debug(ANSI_RED + "Ошибка в классе: " + ANSI_GREEN
                    + getClass().getName() + " "
                    + ANSI_RED + e.getMessage() + ANSI_RESET);
        }
    }
}