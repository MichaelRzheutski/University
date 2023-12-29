package com.solvd.university.persistence.impl;

import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.SubjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryDaoImpl implements SubjectRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String GET_ALL_SUBJECTS = "SELECT * FROM subjects";

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SUBJECTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(resultSet.getLong(1));
                subject.setSubjectName(resultSet.getString(2));
                subjects.add(subject);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить все предметы!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return subjects;
    }
}
