package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Student;

import java.time.LocalDate;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentCommonActions {
    protected Student addStudent() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        MY_LOGGER.info(ANSI_GREEN + "Введите имя студента:" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setFirstName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите фамилию студента:" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setLastName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите дату рождения студента в формате (yyyy-mm-dd):" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите ID контакта:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            student.setStudentContactId(scanner.nextLong());
            MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                    + student.getFirstName() + " " + student.getLastName() + ANSI_RESET + "\n");
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return student;
    }

    protected Student getStudentById() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            student.setStudentId(scanner.nextLong());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }
        return student;
    }

    protected Student editInfo() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);

        if (scanner.hasNextLong()) {
            student.setStudentId(scanner.nextLong());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите новое имя студента:" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setFirstName(scanner.next());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Обновлено имя у студента с ID: " + ANSI_YELLOW
                + student.getStudentId() + ANSI_RESET + "\n");

        return student;
    }

    protected Long deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        long enteredStudentId = 0;

        MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            enteredStudentId = scanner.nextLong();
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Удалён студент с ID: " + ANSI_YELLOW
                + enteredStudentId + ANSI_RESET + "\n");

        return enteredStudentId;
    }
}
