package com.solvd.university.util.menus;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.LecturerService;
import com.solvd.university.service.StudentService;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.menus.menuenums.AdminMenuLecturerItems;
import com.solvd.university.util.menus.menuenums.AdminMenuStudentItems;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminMenu {
    AdminService authorizeAdminJDBC;
    AdminService authorizeAdminMybatis;
    StudentService studentServiceJDBC;
    StudentService studentServiceMybatis;
    LecturerService lecturerServiceJDBC;
    LecturerService lecturerServiceMybatis;

    public AdminMenu(AdminService authorizeAdminJDBC,
                     AdminService authorizeAdminMybatis,
                     StudentService studentServiceJDBC,
                     StudentService studentServiceMybatis,
                     LecturerService lecturerServiceJDBC,
                     LecturerService lecturerServiceMybatis) {
        this.authorizeAdminJDBC = authorizeAdminJDBC;
        this.authorizeAdminMybatis = authorizeAdminMybatis;
        this.studentServiceJDBC = studentServiceJDBC;
        this.studentServiceMybatis = studentServiceMybatis;
        this.lecturerServiceJDBC = lecturerServiceJDBC;
        this.lecturerServiceMybatis = lecturerServiceMybatis;
    }

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
                    MY_LOGGER.info(ANSI_GREEN + "Операции для студента:" + ANSI_RESET);
                    MY_LOGGER.info("[1].  " + AdminMenuStudentItems.ADMIN_SHOW_ALL_STUDENTS);
                    MY_LOGGER.info("[2].  " + AdminMenuStudentItems.ADMIN_ADD_STUDENT);
                    MY_LOGGER.info("[3].  " + AdminMenuStudentItems.ADMIN_FIND_STUDENT_BY_ID);
                    MY_LOGGER.info("[4].  " + AdminMenuStudentItems.ADMIN_UPDATE_STUDENT_INFO);
                    MY_LOGGER.info("[5].  " + AdminMenuStudentItems.ADMIN_DELETE_STUDENT);
                    MY_LOGGER.info("[6].  " + AdminMenuStudentItems.ADMIN_COUNT_STUDENTS);
                    MY_LOGGER.info(ANSI_GREEN + "Операции для преподавателя:" + ANSI_RESET);
                    MY_LOGGER.info("[7].  " + AdminMenuLecturerItems.ADMIN_SHOW_ALL_LECTURERS);
                    MY_LOGGER.info("[8].  " + AdminMenuLecturerItems.ADMIN_ADD_LECTURER);
                    MY_LOGGER.info("[9].  " + AdminMenuLecturerItems.ADMIN_FIND_LECTURER_BY_ID);
                    MY_LOGGER.info("[10]. " + AdminMenuLecturerItems.ADMIN_UPDATE_LECTURER_INFO);
                    MY_LOGGER.info("[11]. " + AdminMenuLecturerItems.ADMIN_DELETE_LECTURER);
                    MY_LOGGER.info("[12]. " + AdminMenuLecturerItems.ADMIN_COUNT_LECTURERS);
                    MY_LOGGER.info("[13]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                    MY_LOGGER.info("[0].  " + GeneralMenuItems.UNIVERSITY_EXIT);
                } else {
                    MY_LOGGER.info(ANSI_GREEN + "Меню администратора: "
                            + controllerType + " + " + xmlConsoleSelector + ANSI_RESET);
                    MY_LOGGER.info(ANSI_GREEN + "Операции для студента:" + ANSI_RESET);
                    MY_LOGGER.info("[1]. " + AdminMenuStudentItems.ADMIN_SHOW_ALL_STUDENTS);
                    MY_LOGGER.info("[2]. " + AdminMenuStudentItems.ADMIN_ADD_STUDENT);
                    MY_LOGGER.info(ANSI_GREEN + "Операции для преподавателя:" + ANSI_RESET);
                    MY_LOGGER.info("[3]. " + AdminMenuLecturerItems.ADMIN_SHOW_ALL_LECTURERS);
                    MY_LOGGER.info("[4]. " + AdminMenuLecturerItems.ADMIN_ADD_LECTURER);
                    MY_LOGGER.info("[5]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                    MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);
                }
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("CONSOLE")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceJDBC.printFullStudentInfo();
                            case 2 -> studentServiceJDBC.enrollStudent(xmlConsoleSelector);
                            case 3 -> studentServiceJDBC.findStudent();
                            case 4 -> studentServiceJDBC.editStudentInfo();
                            case 5 -> studentServiceJDBC.expelStudentById();
                            case 6 -> studentServiceJDBC.printNumberOfEntries();

                            case 7 -> lecturerServiceJDBC.printFullLecturerInfo();
                            case 8 -> lecturerServiceJDBC.employLecturer(xmlConsoleSelector);
                            case 9 -> lecturerServiceJDBC.findLecturer();
                            case 10 -> lecturerServiceJDBC.editLecturerInfo();
                            case 11 -> lecturerServiceJDBC.dismissLecturerById();
                            case 12 -> lecturerServiceJDBC.printNumberOfEntries();
                            case 13 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("STAX")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceJDBC.printFullStudentInfo();
                            case 2 -> studentServiceJDBC.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceJDBC.printFullLecturerInfo();
                            case 4 -> lecturerServiceJDBC.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JAXB")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceJDBC.printFullStudentInfo();
                            case 2 -> studentServiceJDBC.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceJDBC.printFullLecturerInfo();
                            case 4 -> lecturerServiceJDBC.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("JDBC")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JACKSON")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceJDBC.printFullStudentInfo();
                            case 2 -> studentServiceJDBC.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceJDBC.printFullLecturerInfo();
                            case 4 -> lecturerServiceJDBC.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("STAX")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceMybatis.printFullStudentInfo();
                            case 2 -> studentServiceMybatis.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceMybatis.printFullLecturerInfo();
                            case 4 -> lecturerServiceMybatis.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JAXB")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceMybatis.printFullStudentInfo();
                            case 2 -> studentServiceMybatis.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceMybatis.printFullLecturerInfo();
                            case 4 -> lecturerServiceMybatis.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else if (controllerType.getControllerType().equals("MYBATIS")
                            && xmlConsoleSelector.getXmlConsoleSelector().equals("JACKSON")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceMybatis.printFullStudentInfo();
                            case 2 -> studentServiceMybatis.enrollStudent(xmlConsoleSelector);

                            case 3 -> lecturerServiceMybatis.printFullLecturerInfo();
                            case 4 -> lecturerServiceMybatis.employLecturer(xmlConsoleSelector);
                            case 5 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> studentServiceMybatis.printFullStudentInfo();
                            case 2 -> studentServiceMybatis.enrollStudent(xmlConsoleSelector);
                            case 3 -> studentServiceMybatis.findStudent();
                            case 4 -> studentServiceMybatis.editStudentInfo();
                            case 5 -> studentServiceMybatis.expelStudentById();
                            case 6 -> studentServiceMybatis.printNumberOfEntries();

                            case 7 -> lecturerServiceMybatis.printFullLecturerInfo();
                            case 8 -> lecturerServiceMybatis.employLecturer(xmlConsoleSelector);
                            case 9 -> lecturerServiceMybatis.findLecturer();
                            case 10 -> lecturerServiceMybatis.editLecturerInfo();
                            case 11 -> lecturerServiceMybatis.dismissLecturerById();
                            case 12 -> lecturerServiceMybatis.printNumberOfEntries();
                            case 13 -> isExit = true;
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
