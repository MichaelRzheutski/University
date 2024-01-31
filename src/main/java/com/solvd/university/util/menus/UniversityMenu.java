package com.solvd.university.util.menus;

import com.solvd.university.persistence.impl.jdbc.*;
import com.solvd.university.persistence.impl.mybatis.*;
import com.solvd.university.service.*;
import com.solvd.university.service.impl.commonactions.LecturerDepartmentServiceCA;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCA;
import com.solvd.university.service.impl.commonactions.SubjectServiceCA;
import com.solvd.university.service.impl.jdbc.*;
import com.solvd.university.service.impl.mybatis.*;
import com.solvd.university.service.impl.parsers.impl.*;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.ParserSelectors;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.UniversityMenuItems;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class UniversityMenu {
    private final SubjectService subjectServiceJDBC = new SubjectServiceJdbcImpl(
            new StudentRepositoryJdbcImpl(),
            new SubjectRepositoryJdbcImpl(),
            new StudentServiceJdbcImpl(
                    new StaxOperationsStudent(),
                    new JaxbOperationsStudent(),
                    new JacksonOperationsStudent(),
                    new StudentDepartmentServiceJdbcImpl(
                            new StudentRepositoryJdbcImpl(),
                            new DepartmentRepositoryJdbcImpl(),
                            new StudentDepartmentServiceCA()
                    ),
                    new StudentContactRepositoryJdbcImpl(),
                    new StudentRepositoryJdbcImpl(),
                    new StudentContactServiceJdbcImpl(
                            new StaxOperationsStudent(),
                            new JaxbOperationsStudent(),
                            new JacksonOperationsStudent(),
                            new StudentContactRepositoryJdbcImpl()
                    )
            ),
            new SubjectServiceCA()
    );
    private final SubjectService subjectServiceMybatis = new SubjectServiceMybatisImpl(
            new StudentRepositoryMybatisImpl(),
            new SubjectRepositoryMybatisImpl(),
            new StudentServiceMybatisImpl(
                    new StaxOperationsStudent(),
                    new JaxbOperationsStudent(),
                    new JacksonOperationsStudent(),
                    new StudentDepartmentServiceMybatisImpl(
                            new StudentRepositoryMybatisImpl(),
                            new DepartmentRepositoryMybatisImpl(),
                            new StudentDepartmentServiceCA()
                    ),
                    new StudentContactRepositoryMybatisImpl(),
                    new StudentRepositoryMybatisImpl(),
                    new StudentContactServiceMybatisImpl(
                            new StaxOperationsStudent(),
                            new JaxbOperationsStudent(),
                            new JacksonOperationsStudent(),
                            new StudentContactRepositoryMybatisImpl()
                    )
            ),
            new SubjectServiceCA()
    );
    private final AdminService authorizeAdminJDBC = new AdminServiceJdbcImpl();
    private final AdminService authorizeAdminMybatis = new AdminServiceMybatisImpl();
    private final StudentService studentServiceJDBC = new StudentServiceJdbcImpl(
            new StaxOperationsStudent(),
            new JaxbOperationsStudent(),
            new JacksonOperationsStudent(),
            new StudentDepartmentServiceJdbcImpl(
                    new StudentRepositoryJdbcImpl(),
                    new DepartmentRepositoryJdbcImpl(),
                    new StudentDepartmentServiceCA()
            ),
            new StudentContactRepositoryJdbcImpl(),
            new StudentRepositoryJdbcImpl(),
            new StudentContactServiceJdbcImpl(
                    new StaxOperationsStudent(),
                    new JaxbOperationsStudent(),
                    new JacksonOperationsStudent(),
                    new StudentContactRepositoryJdbcImpl()
            )
    );
    private final StudentService studentServiceMybatis = new StudentServiceMybatisImpl(
            new StaxOperationsStudent(),
            new JaxbOperationsStudent(),
            new JacksonOperationsStudent(),
            new StudentDepartmentServiceMybatisImpl(
                    new StudentRepositoryMybatisImpl(),
                    new DepartmentRepositoryMybatisImpl(),
                    new StudentDepartmentServiceCA()
            ),
            new StudentContactRepositoryMybatisImpl(),
            new StudentRepositoryMybatisImpl(),
            new StudentContactServiceMybatisImpl(
                    new StaxOperationsStudent(),
                    new JaxbOperationsStudent(),
                    new JacksonOperationsStudent(),
                    new StudentContactRepositoryMybatisImpl()
            )
    );

    private final LecturerService lecturerServiceJDBC = new LecturerServiceJdbcImpl(
            new StaxOperationsLecturer(),
            new JaxbOperationsLecturer(),
            new JacksonOperationsLecturer(),
            new LecturerDepartmentServiceJdbcImpl(
                    new LecturerRepositoryJdbcImpl(),
                    new DepartmentRepositoryJdbcImpl(),
                    new LecturerDepartmentServiceCA()
            ),
            new LecturerContactRepositoryJdbcImpl(),
            new LecturerRepositoryJdbcImpl(),
            new LecturerContactServiceJdbcImpl(
                    new StaxOperationsLecturer(),
                    new JaxbOperationsLecturer(),
                    new JacksonOperationsLecturer(),
                    new LecturerContactRepositoryJdbcImpl()
            )
    );
    private final LecturerService lecturerServiceMybatis = new LecturerServiceMybatisImpl(
            new StaxOperationsLecturer(),
            new JaxbOperationsLecturer(),
            new JacksonOperationsLecturer(),
            new LecturerDepartmentServiceMybatisImpl(
                    new LecturerRepositoryMybatisImpl(),
                    new DepartmentRepositoryMybatisImpl(),
                    new LecturerDepartmentServiceCA()
            ),
            new LecturerContactRepositoryJdbcImpl(),
            new LecturerRepositoryMybatisImpl(),
            new LecturerContactServiceMybatisImpl(
                    new StaxOperationsLecturer(),
                    new JaxbOperationsLecturer(),
                    new JacksonOperationsLecturer(),
                    new LecturerContactRepositoryMybatisImpl()
            )
    );

    private final GradeBookService gradeBookJDBC = new GradeBookServiceJdbcImpl(
            new StudentRepositoryJdbcImpl(),
            new SubjectRepositoryJdbcImpl(),
            new SubjectServiceCA()
    );
    private final GradeBookService gradeBookMybatis = new GradeBookServiceMybatisImpl(
            new StudentRepositoryMybatisImpl(),
            new SubjectRepositoryMybatisImpl(),
            new SubjectServiceCA()
    );

    private final StudentMenu studentMenu = new StudentMenu(
            subjectServiceJDBC,
            subjectServiceMybatis
    );
    private final AdminMenu adminMenu = new AdminMenu(
            authorizeAdminJDBC,
            authorizeAdminMybatis,
            studentServiceJDBC,
            studentServiceMybatis,
            lecturerServiceJDBC,
            lecturerServiceMybatis
    );

    private final LecturerMenu lecturerMenu = new LecturerMenu(
            gradeBookJDBC,
            gradeBookMybatis
    );

    public void showUniversityMenu(
            Scanner scanner, ControllerTypes controllerType,
            ParserSelectors parserSelector) throws NotNumberException {
        int option;
        boolean isExit = false;

        try {
            while (!isExit) {
                MY_LOGGER.info(ANSI_GREEN + "Меню университета: " +
                        controllerType + " + " + parserSelector + ANSI_RESET);
                MY_LOGGER.info("[1]. " + UniversityMenuItems.UNIVERSITY_STUDENT_OPERATIONS);
                MY_LOGGER.info("[2]. " + UniversityMenuItems.UNIVERSITY_LECTURER_OPERATIONS);
                MY_LOGGER.info("[3]. " + UniversityMenuItems.UNIVERSITY_ADMIN_OPERATIONS);
                MY_LOGGER.info("[4]. " + GeneralMenuItems.UNIVERSITY_PREVIOUS_MENU);
                MY_LOGGER.info("[0]. " + GeneralMenuItems.UNIVERSITY_EXIT);

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();

                    switch (option) {
                        case 0 -> System.exit(0);
                        case 1 -> studentMenu.showStudentMenu(scanner, controllerType, parserSelector);
                        case 2 -> lecturerMenu.showLecturerMenu(scanner, controllerType, parserSelector);
                        case 3 -> adminMenu.showAdminMenu(scanner, controllerType, parserSelector);
                        case 4 -> isExit = true;
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
