package com.solvd.university.util.menus;

import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.proxy.AllSubjectsJdbcProxy;
import com.solvd.university.service.impl.proxy.AllSubjectsMybatisProxy;
import com.solvd.university.service.impl.proxy.AllSubjectsProxy;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.StudentMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public final class StudentMenu {
    private final AllSubjectsProxy allSubjectsJdbcProxy = new AllSubjectsJdbcProxy();
    private final AllSubjectsProxy allSubjectsMybatisProxy = new AllSubjectsMybatisProxy();
    private final SubjectService subjectServiceJDBC;
    private final SubjectService subjectServiceMybatis;

    public StudentMenu(SubjectService subjectServiceJDBC, SubjectService subjectServiceMybatis) {
        this.subjectServiceJDBC = subjectServiceJDBC;
        this.subjectServiceMybatis = subjectServiceMybatis;
    }

    public void showStudentMenu(
            Scanner scanner, ControllerTypes controllerType,
            XmlConsoleSelectors xmlConsoleSelector) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню студента: " + controllerType + ANSI_RESET);
                MY_LOGGER.info("[1]. " + StudentMenuItems.STUDENT_SHOW_ALL_STUDENT_SUBJECTS_PROXY);
                MY_LOGGER.info("[2]. " + StudentMenuItems.STUDENT_SHOW_ALL_STUDENT_SUBJECTS);
                MY_LOGGER.info("[3]. " + StudentMenuItems.STUDENT_SHOW_STUDENT_SUBJECTS);
                MY_LOGGER.info("[4]. " + StudentMenuItems.STUDENT_SHOW_GRADES);
                MY_LOGGER.info("[5]. " + StudentMenuItems.STUDENT_TAKE_EXAM);
                MY_LOGGER.info("[6]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    if (controllerType.getControllerType().equals("JDBC")) {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> allSubjectsJdbcProxy.printAllSubjects();
                            case 2 -> subjectServiceJDBC.printAllSubjects();
                            case 3 -> subjectServiceJDBC.getStudentAllSubjects();
                            case 4 -> subjectServiceJDBC.showStudentPerformance();
                            case 5 -> subjectServiceJDBC.takeExam();
                            case 6 -> isExit = true;
                            default -> MY_LOGGER.info(
                                    String.format("%sНеверная операция, попробуйте ещё раз!%s\n",
                                            ANSI_RED, ANSI_RESET)
                            );
                        }
                    } else {
                        switch (option) {
                            case 0 -> System.exit(0);
                            case 1 -> allSubjectsMybatisProxy.printAllSubjects();
                            case 2 -> subjectServiceMybatis.printAllSubjects();
                            case 3 -> subjectServiceMybatis.getStudentAllSubjects();
                            case 4 -> subjectServiceMybatis.showStudentPerformance();
                            case 5 -> subjectServiceMybatis.takeExam();
                            case 6 -> isExit = true;
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