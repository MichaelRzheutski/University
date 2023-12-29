package com.solvd.university.util.menus;

import com.solvd.university.domain.Admin;
import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.domain.logic.AdminLogic;
import com.solvd.university.persistence.impl.AdminRepositoryImpl;
import com.solvd.university.persistence.impl.StudentContactImpl;
import com.solvd.university.persistence.impl.StudentRepositoryImpl;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.menuenums.AdminMenuItems;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminMenu {
    private static final Admin ADMIN = new Admin();
    private static Student student;
    private static StudentContact studentContact;

    public void showAdminMenu(Scanner scanner) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            new AdminRepositoryImpl().setAdminCredentials(ADMIN);

            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню администратора: " + ANSI_RESET);
                MY_LOGGER.info("[1]. " + AdminMenuItems.ADMIN_SHOW_ALL_STUDENTS);
                MY_LOGGER.info("[2]. " + AdminMenuItems.ADMIN_ADD_STUDENT_CONTACT);
                MY_LOGGER.info("[3]. " + AdminMenuItems.ADMIN_ADD_STUDENT);
                MY_LOGGER.info("[4]. " + AdminMenuItems.ADMIN_FIND_STUDENT_BY_ID);
                MY_LOGGER.info("[5]. " + AdminMenuItems.ADMIN_UPDATE_STUDENT_INFO);
                MY_LOGGER.info("[6]. " + AdminMenuItems.ADMIN_DELETE_STUDENT);
                MY_LOGGER.info("[7]. " + AdminMenuItems.ADMIN_COUNT_STUDENTS);
                MY_LOGGER.info("[8]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> new AdminLogic().getAllStudents();
                        case 2 -> new StudentContactImpl().create(studentContact);
                        case 3 -> new StudentRepositoryImpl().create(student);
                        case 4 -> new StudentRepositoryImpl().findById();
                        case 5 -> new StudentRepositoryImpl().update(student);
                        case 6 -> new StudentRepositoryImpl().deleteById();
                        case 7 -> new StudentRepositoryImpl().countOfEntries();
                        case 8 -> isExit = true;
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
