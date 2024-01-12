package com.solvd.university.util.menus;

import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.UniversityMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class UniversityMenu {
    private static final StudentMenu STUDENT_MENU = new StudentMenu();
    private static final AdminMenu ADMIN_MENU = new AdminMenu();

    public void showUniversityMenu(Scanner scanner, String controllerType, String xmlConsoleSelector) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню университета: " +
                        controllerType + " + " + xmlConsoleSelector + ANSI_RESET);
                MY_LOGGER.info("[1]. " + UniversityMenuItems.UNIVERSITY_STUDENT_OPERATIONS);
                MY_LOGGER.info("[2]. " + UniversityMenuItems.UNIVERSITY_ADMIN_OPERATIONS);
                MY_LOGGER.info("[3]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> STUDENT_MENU.showStudentMenu(scanner, controllerType, xmlConsoleSelector);
                        case 2 -> ADMIN_MENU.showAdminMenu(scanner, controllerType, xmlConsoleSelector);
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
