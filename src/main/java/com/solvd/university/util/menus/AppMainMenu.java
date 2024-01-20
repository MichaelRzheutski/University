package com.solvd.university.util.menus;

import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.menuenums.AppMainMenuItems;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import org.apache.log4j.BasicConfigurator;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;
import static com.solvd.university.util.menus.enums.ControllerTypes.JDBC;
import static com.solvd.university.util.menus.enums.ControllerTypes.MYBATIS;

public final class AppMainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private static final ParserSelectorMenu PARSER_SELECTOR_MENU = new ParserSelectorMenu();

    public void mainMenu() {
        BasicConfigurator.configure();
        int option;

        try (scanner) {
            while (true) {
                MY_LOGGER.info(ANSI_GREEN + "Выберите: " + ANSI_RESET);
                MY_LOGGER.info("[1]. " + AppMainMenuItems.UNIVERSITY_JDBC);
                MY_LOGGER.info("[2]. " + AppMainMenuItems.UNIVERSITY_MYBATIS);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> PARSER_SELECTOR_MENU.selectParser(scanner, JDBC);
                        case 2 -> PARSER_SELECTOR_MENU.selectParser(scanner, MYBATIS);
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