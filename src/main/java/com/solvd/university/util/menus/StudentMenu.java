package com.solvd.university.util.menus;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.service.impl.StudentServiceImpl;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.StudentMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public final class StudentMenu {
    private static Subject subject;
    private static Student student;

    public void showStudentMenu(Scanner scanner) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню студента: " + ANSI_RESET);
                MY_LOGGER.info("[1]. " + StudentMenuItems.STUDENT_SHOW_ALL_STUDENT_SUBJECTS);
                MY_LOGGER.info("[2]. " + StudentMenuItems.STUDENT_SHOW_STUDENT_SUBJECTS);
                MY_LOGGER.info("[3]. " + StudentMenuItems.STUDENT_SHOW_GRADES);
                MY_LOGGER.info("[4]. " + StudentMenuItems.STUDENT_TAKE_EXAM);
                MY_LOGGER.info("[5]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> new StudentServiceImpl().printAllSubjects();
                        case 2 -> new StudentServiceImpl().getStudentSubjects();
                        case 3 -> new StudentServiceImpl().showStudentPerformance();
                        case 4 -> new StudentServiceImpl().takeExam();
                        case 5 -> isExit = true;
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