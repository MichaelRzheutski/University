package com.solvd.university.util.menus;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.jdbc.AdminServiceJdbcImpl;
import com.solvd.university.service.impl.jdbc.StudentServiceJdbcImpl;
import com.solvd.university.service.impl.mybatis.AdminServiceMybatisImpl;
import com.solvd.university.service.impl.mybatis.StudentServiceMybatisImpl;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.menus.menuenums.AdminMenuItems;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminMenu {
    private final AdminService authorizeAdminJDBC = new AdminServiceJdbcImpl();
    private final AdminService authorizeAdminMybatis = new AdminServiceMybatisImpl();

    public void showAdminMenu(
            Scanner scanner, ControllerTypes controllerType,
            XmlConsoleSelectors xmlConsoleSelector) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            if (controllerType.getControllerType().equals("JDBC")) {
                authorizeAdminJDBC.authorizeAdmin();
            } else {
                authorizeAdminMybatis.authorizeAdmin();
            }

            while (!isExit) {
                if (controllerType.getControllerType().equals("JDBC")
                        && xmlConsoleSelector.getXmlConsoleSelector().equals("CONSOLE")
                        || controllerType.getControllerType().equals("MYBATIS")
                        && xmlConsoleSelector.getXmlConsoleSelector().equals("CONSOLE")) {
                    MY_LOGGER.info(ANSI_GREEN + "Меню администратора: "
                            + controllerType + " + " + xmlConsoleSelector + ANSI_RESET);
                    MY_LOGGER.info("[1]. " + AdminMenuItems.ADMIN_SHOW_ALL_STUDENTS);
                    MY_LOGGER.info("[2]. " + AdminMenuItems.ADMIN_ADD_STUDENT);
                    MY_LOGGER.info("[3]. " + AdminMenuItems.ADMIN_FIND_STUDENT_BY_ID);
                    MY_LOGGER.info("[4]. " + AdminMenuItems.ADMIN_UPDATE_STUDENT_INFO);
                    MY_LOGGER.info("[5]. " + AdminMenuItems.ADMIN_DELETE_STUDENT);
                    MY_LOGGER.info("[6]. " + AdminMenuItems.ADMIN_COUNT_STUDENTS);
                    MY_LOGGER.info("[7]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                    MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);
                } else {
                    MY_LOGGER.info(ANSI_GREEN + "Меню администратора: "
                            + controllerType + " + " + xmlConsoleSelector + ANSI_RESET);
                    MY_LOGGER.info("[1]. " + AdminMenuItems.ADMIN_SHOW_ALL_STUDENTS);
                    MY_LOGGER.info("[2]. " + AdminMenuItems.ADMIN_ADD_STUDENT);
                    MY_LOGGER.info("[3]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                    MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);
                }
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("CONSOLE")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceJdbcImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceJdbcImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> new StudentServiceJdbcImpl().findStudent();
                            case 4 -> new StudentServiceJdbcImpl().editStudentInfo();
                            case 5 -> new StudentServiceJdbcImpl().expelStudentById();
                            case 6 -> new StudentServiceJdbcImpl().printNumberOfEntries();
                            case 7 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("STAX")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceJdbcImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceJdbcImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JAXB")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceJdbcImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceJdbcImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JACKSON")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceJdbcImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceJdbcImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("STAX")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceMybatisImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceMybatisImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JAXB")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceMybatisImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceMybatisImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JACKSON")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceMybatisImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceMybatisImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> new StudentServiceMybatisImpl().printFullStudentInfo();
                            case 2 -> new StudentServiceMybatisImpl().enrollStudent(xmlConsoleSelector);
                            case 3 -> new StudentServiceMybatisImpl().findStudent();
                            case 4 -> new StudentServiceMybatisImpl().editStudentInfo();
                            case 5 -> new StudentServiceMybatisImpl().expelStudentById();
                            case 6 -> new StudentServiceMybatisImpl().printNumberOfEntries();
                            case 7 -> isExit = true;
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
