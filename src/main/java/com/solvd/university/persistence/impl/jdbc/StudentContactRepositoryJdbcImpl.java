package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.StudentContactRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentContactRepositoryJdbcImpl implements StudentContactRepository {
    public static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_STUDENT_CONTACTS =
            "INSERT INTO student_contacts (phone, email, student_id) VALUES (?, ?, ?);";

    @Override
    public void createStudentContact(Student student, StudentContact studentContact) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_STUDENT_CONTACTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, studentContact.getPhone());
            preparedStatement.setString(2, studentContact.getEmail());
            preparedStatement.setLong(3, student.getStudentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
