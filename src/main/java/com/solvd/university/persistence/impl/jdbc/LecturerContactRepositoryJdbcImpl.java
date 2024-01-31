package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.LecturerContact;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.LecturerContactRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerContactRepositoryJdbcImpl implements LecturerContactRepository {
    public static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_LECTURER_CONTACTS =
            "INSERT INTO lecturer_contacts (lecturer_id, phone, email) VALUES (?, ?, ?);";

    private static final String GET_LECTURER_CONTACTS = "SELECT * FROM lecturer_contacts;";

    @Override
    public void createLecturerContact(LecturerContact lecturerContact) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_LECTURER_CONTACTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, lecturerContact.getLecturerId());
            preparedStatement.setString(2, lecturerContact.getPhone());
            preparedStatement.setString(3, lecturerContact.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<LecturerContact> getAllLecturerContacts() {
        Connection connection = CONNECTION_POOL.getConnection();
        List<LecturerContact> lecturerContacts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LECTURER_CONTACTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LecturerContact lecturerContact = new LecturerContact();
                lecturerContact.setLecturerContactId(resultSet.getLong(1));
                lecturerContact.setLecturerId(resultSet.getLong(2));
                lecturerContact.setPhone(resultSet.getString(3));
                lecturerContact.setEmail(resultSet.getString(4));
                lecturerContacts.add(lecturerContact);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить контакт!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return lecturerContacts;
    }
}
