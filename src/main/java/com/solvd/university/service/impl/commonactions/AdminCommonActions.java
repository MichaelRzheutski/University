package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Admin;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.ConsoleColors.ANSI_RESET;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminCommonActions {
    private void setAdminCredentials() {
        Properties property = new Properties();
        Admin admin = new Admin();

        try (FileInputStream fis = new FileInputStream("src/main/resources/admin_credentials.properties")) {
            property.load(fis);

            admin.setLogin(property.getProperty("ADMIN_LOGIN"));
            admin.setPassword(property.getProperty("ADMIN_PASSWORD"));

        } catch (IOException e) {
            throw new RuntimeException("Невозможно прочитать property файл!", e);
        }

        isAdmin(admin);
    }

    private void isAdmin(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        String enteredAdminLogin = "";
        String enteredAdminPassword = "";

        while (true) {
            MY_LOGGER.info(ANSI_GREEN + "Введите логин администратора" + ANSI_RESET);
            if (scanner.hasNext()) {
                enteredAdminLogin = scanner.nextLine();
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

            MY_LOGGER.info(ANSI_GREEN + "Введите пароль администратора" + ANSI_RESET);
            if (scanner.hasNext()) {
                enteredAdminPassword = scanner.nextLine();
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

            if (enteredAdminLogin.equals(admin.getLogin()) && enteredAdminPassword.equals(admin.getPassword())) {
                MY_LOGGER.info(ANSI_GREEN + "Авторизация выполнена успешно\n" + ANSI_RESET);
                break;
            } else {
                MY_LOGGER.info(ANSI_RED + "Логин и пароль не совпадают, попробуйте ещё раз!\n" + ANSI_RESET);
            }
        }
    }

    public void getAdminAccess() {
        setAdminCredentials();
    }

    public void printWholeStudentInfo() {
        List<Student> studentList = new StudentRepositoryJdbcImpl().findAll();

        for (Student student : studentList) {
            MY_LOGGER.info(student.getStudentId() + " | " + ANSI_YELLOW + student.getFirstName() + " | "
                    + student.getLastName() + " | " + student.getDateOfBirth() + " | "
                    + student.getAverageScore() + ANSI_RESET
            );
        }
        System.out.println();
    }
}
