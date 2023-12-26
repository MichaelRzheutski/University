package com.solvd.university.persistence.impl;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.StudentContactRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentContactImpl implements StudentContactRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_STUDENT_CONTACTS =
            "INSERT INTO student_contacts(phone, email) values(?, ?);";

    @Override
    public void create(StudentContact studentContact) {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_STUDENT_CONTACTS, Statement.RETURN_GENERATED_KEYS)) {

            String enteredPhone;
            String enteredEmail;

            MY_LOGGER.info(ANSI_GREEN + "Введите номер телефона:" + ANSI_RESET);
            if (scanner.hasNext()) {
                enteredPhone = scanner.nextLine();
                preparedStatement.setString(1, enteredPhone);

                MY_LOGGER.info(ANSI_GREEN + "Введите Email:" + ANSI_RESET);
                if (scanner.hasNext()) {
                    enteredEmail = scanner.nextLine();
                    preparedStatement.setString(2, enteredEmail);

                    preparedStatement.executeUpdate();

                    MY_LOGGER.info(ANSI_GREEN + "Контакт был добавлен в базу: " + ANSI_YELLOW
                            + enteredPhone + " " + enteredEmail + ANSI_RESET + "\n");
                }
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void findById() {

    }

    @Override
    public List<StudentContact> findAll() {
        return null;
    }

    @Override
    public void update(StudentContact studentContact) {

    }

    @Override
    public void deleteById() {

    }

    @Override
    public Long countOfEntries() {
        return null;
    }
}
