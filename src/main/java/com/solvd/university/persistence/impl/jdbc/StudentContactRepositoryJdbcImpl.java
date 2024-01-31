package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.StudentContactRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentContactRepositoryJdbcImpl implements StudentContactRepository {
    public static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_STUDENT_CONTACTS =
            "INSERT INTO student_contacts (student_id, phone, email) VALUES (?, ?, ?);";

    private static final String GET_STUDENT_CONTACTS = "SELECT * FROM student_contacts;";

    @Override
    public void createStudentContact(StudentContact studentContact) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_STUDENT_CONTACTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, studentContact.getStudentId());
            preparedStatement.setString(2, studentContact.getPhone());
            preparedStatement.setString(3, studentContact.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<StudentContact> getAllStudentContacts() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<StudentContact> studentContacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_CONTACTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentContact studentContact = new StudentContact();
                studentContact.setStudentContactId(resultSet.getLong(1));
                studentContact.setStudentId(resultSet.getLong(2));
                studentContact.setPhone(resultSet.getString(3));
                studentContact.setEmail(resultSet.getString(4));
                studentContacts.add(studentContact);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return studentContacts;
    }
}
