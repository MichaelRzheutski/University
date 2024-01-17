package com.solvd.university.util.menus;

import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.XmlConsoleSelectorMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;
import static com.solvd.university.util.menus.enums.XmlConsoleSelectors.*;

public class XmlConsoleSelectorMenu {
    private static final UniversityMenu UNIVERSITY_MENU = new UniversityMenu();

    public void xmlConsoleSelectorMenu(Scanner scanner, ControllerTypes controllerType) {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Выберите парсер: " + ANSI_RESET);
                MY_LOGGER.info("[1]. " + XmlConsoleSelectorMenuItems.DATA_PROVIDER_CONSOLE);
                MY_LOGGER.info("[2]. " + XmlConsoleSelectorMenuItems.DATA_PROVIDER_XML);
                MY_LOGGER.info("[3]. " + XmlConsoleSelectorMenuItems.DATA_PROVIDER_XML_JAXB);
                MY_LOGGER.info("[4]. " + XmlConsoleSelectorMenuItems.DATA_PROVIDER_JACKSON);
                MY_LOGGER.info("[5]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> UNIVERSITY_MENU.showUniversityMenu(scanner, controllerType, CONSOLE);
                        case 2 -> UNIVERSITY_MENU.showUniversityMenu(scanner, controllerType, STAX);
                        case 3 -> UNIVERSITY_MENU.showUniversityMenu(scanner, controllerType, JAXB);
                        case 4 -> UNIVERSITY_MENU.showUniversityMenu(scanner, controllerType, JACKSON);
                        case 5 -> isExit = true;
                        default -> MY_LOGGER.debug(
                                ANSI_RED + "Неверная операция, " +
                                        "попробуйте ещё раз!\n" + ANSI_RESET
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
