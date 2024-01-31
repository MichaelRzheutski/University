package com.solvd.university.util.menus;

import com.solvd.university.service.GradeBookService;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.ParserSelectors;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.LecturerMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class LecturerMenu {

    private final GradeBookService gradeBookServiceJDBC;
    private final GradeBookService gradeBookServiceMybatis;

    public LecturerMenu(GradeBookService gradeBookServiceJDBC, GradeBookService gradeBookServiceMybatis) {
        this.gradeBookServiceJDBC = gradeBookServiceJDBC;
        this.gradeBookServiceMybatis = gradeBookServiceMybatis;
    }

    public void showLecturerMenu(
            Scanner scanner, ControllerTypes controllerType,
            ParserSelectors parserSelector) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню преподавателя: " + controllerType + ANSI_RESET);
                MY_LOGGER.info("[1]. " + LecturerMenuItems.LECTURER_SHOW_GRADE_BOOK);
                MY_LOGGER.info("[2]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    if (controllerType.getControllerType().equals("JDBC")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> gradeBookServiceJDBC.printGradeBook();
                            case 2 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> gradeBookServiceMybatis.printGradeBook();
                            case 2 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
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
