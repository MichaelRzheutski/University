package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Admin;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.AdminService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminServiceMybatisImpl implements AdminService {
    public void setAdminCredentials(Admin admin) {
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/admin_credentials.properties")) {
            property.load(fis);

            admin.setLogin(property.getProperty("ADMIN_LOGIN"));
            admin.setPassword(property.getProperty("ADMIN_PASSWORD"));

        } catch (IOException e) {
            throw new RuntimeException("Невозможно прочитать property файл!", e);
        }

        authorize(admin);
    }

    @Override
    public void authorize(Admin admin) {
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

    @Override
    public void printAllStudents() {
        List<Student> studentList = new StudentRepositoryMybatisImpl().findAll();

        for (Student student : studentList) {
            MY_LOGGER.info(student.getStudentId() + " | " + ANSI_YELLOW + student.getFirstName() + " | "
                    + student.getLastName() + " | " + student.getDateOfBirth() + " | "
                    + student.getAverageScore() + ANSI_RESET
            );
        }
        System.out.println();
    }
}
