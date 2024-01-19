package com.solvd.university.util.menus;

import com.solvd.university.persistence.impl.jdbc.DepartmentRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.mybatis.DepartmentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.SubjectRepositoryMybatisImpl;
import com.solvd.university.service.AdminService;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.DepartmentCommonActionsService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;
import com.solvd.university.service.impl.jdbc.*;
import com.solvd.university.service.impl.mybatis.*;
import com.solvd.university.util.exceptions.NotNumberException;
import com.solvd.university.util.menus.enums.ControllerTypes;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.menus.menuenums.GeneralMenuItems;
import com.solvd.university.util.menus.menuenums.UniversityMenuItems;
import com.solvd.university.util.parsers.JacksonServiceOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxServiceOperations;

import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class UniversityMenu {
    private final SubjectService subjectServiceJDBC = new SubjectServiceJdbcImpl(
            new StudentRepositoryJdbcImpl(),
            new SubjectRepositoryJdbcImpl(),
            new StudentServiceJdbcImpl(
                    new StaxServiceOperations(),
                    new JaxbOperations(),
                    new JacksonServiceOperations(),
                    new DepartmentJdbcImplService(
                            new StudentRepositoryJdbcImpl(),
                            new DepartmentRepositoryJdbcImpl(),
                            new DepartmentCommonActionsService()
                    ),
                    new StudentContactRepositoryJdbcImpl(),
                    new StudentRepositoryJdbcImpl(),
                    new StudentContactServiceJdbcImpl(
                            new StaxServiceOperations(),
                            new JaxbOperations(),
                            new JacksonServiceOperations(),
                            new StudentContactRepositoryJdbcImpl()
                    )
            ),
            new SubjectServiceCommonActions()
    );
    private final SubjectService subjectServiceMybatis = new SubjectServiceMybatisImpl(
            new StudentRepositoryMybatisImpl(),
            new SubjectRepositoryMybatisImpl(),
            new StudentServiceMybatisImpl(
                    new StaxServiceOperations(),
                    new JaxbOperations(),
                    new JacksonServiceOperations(),
                    new DepartmentMybatisImplService(
                            new StudentRepositoryMybatisImpl(),
                            new DepartmentRepositoryMybatisImpl(),
                            new DepartmentCommonActionsService()
                    ),
                    new StudentContactRepositoryMybatisImpl(),
                    new StudentRepositoryMybatisImpl(),
                    new StudentContactServiceMybatisImpl(
                            new StaxServiceOperations(),
                            new JaxbOperations(),
                            new JacksonServiceOperations(),
                            new StudentContactRepositoryMybatisImpl()
                    )
            ),
            new SubjectServiceCommonActions()
    );
    private final AdminService authorizeAdminJDBC = new AdminServiceJdbcImpl();
    private final AdminService authorizeAdminMybatis = new AdminServiceMybatisImpl();
    private final StudentService studentServiceJDBC = new StudentServiceJdbcImpl(
            new StaxServiceOperations(),
            new JaxbOperations(),
            new JacksonServiceOperations(),
            new DepartmentJdbcImplService(
                    new StudentRepositoryJdbcImpl(),
                    new DepartmentRepositoryJdbcImpl(),
                    new DepartmentCommonActionsService()
            ),
            new StudentContactRepositoryJdbcImpl(),
            new StudentRepositoryJdbcImpl(),
            new StudentContactServiceJdbcImpl(
                    new StaxServiceOperations(),
                    new JaxbOperations(),
                    new JacksonServiceOperations(),
                    new StudentContactRepositoryJdbcImpl()
            )
    );
    private final StudentService studentServiceMybatis = new StudentServiceMybatisImpl(
            new StaxServiceOperations(),
            new JaxbOperations(),
            new JacksonServiceOperations(),
            new DepartmentMybatisImplService(
                    new StudentRepositoryMybatisImpl(),
                    new DepartmentRepositoryMybatisImpl(),
                    new DepartmentCommonActionsService()
            ),
            new StudentContactRepositoryMybatisImpl(),
            new StudentRepositoryMybatisImpl(),
            new StudentContactServiceMybatisImpl(
                    new StaxServiceOperations(),
                    new JaxbOperations(),
                    new JacksonServiceOperations(),
                    new StudentContactRepositoryMybatisImpl()
            )
    );
    private final StudentMenu studentMenu = new StudentMenu(
            subjectServiceJDBC,
            subjectServiceMybatis
    );
    private final AdminMenu adminMenu = new AdminMenu(
            authorizeAdminJDBC,
            authorizeAdminMybatis,
            studentServiceJDBC,
            studentServiceMybatis
    );

    public void showUniversityMenu(
            Scanner scanner, ControllerTypes controllerType,
            XmlConsoleSelectors xmlConsoleSelector) throws NotNumberException {
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
                        case 1 -> studentMenu.showStudentMenu(scanner, controllerType, xmlConsoleSelector);
                        case 2 -> adminMenu.showAdminMenu(scanner, controllerType, xmlConsoleSelector);
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
