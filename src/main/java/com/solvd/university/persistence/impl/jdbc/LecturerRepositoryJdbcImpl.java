package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.LecturerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerRepositoryJdbcImpl implements LecturerRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String GET_ALL_LECTURERS =
            "SELECT lecturer_id, first_name, last_name FROM lecturers";
    private static final String INSERT_INTO_LECTURERS =
            "INSERT INTO lecturers (first_name, last_name) VALUES (?, ?);";
    private static final String FIND_LECTURER_BY_ID = "SELECT * FROM lecturers WHERE lecturer_id = ?;";
    private static final String UPDATE_LECTURER_INFO = "UPDATE lecturers " +
            "SET first_name = ?, last_name = ? WHERE lecturer_id = ?;";
    private static final String DELETE_FROM_LECTURERS = "DELETE FROM lecturers WHERE lecturer_id = ?;";
    private static final String COUNT_LECTURER_ENTRIES = "SELECT COUNT(*) AS lecturers_count FROM lecturers;";

    @Override
    public void create(Lecturer lecturer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_LECTURERS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lecturer.getFirstName());
            preparedStatement.setString(2, lecturer.getLastName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                lecturer.setLecturerId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно создать преподавателя!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Lecturer findById(Lecturer lecturer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURER_BY_ID)) {
            preparedStatement.setLong(1, lecturer.getLecturerId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            lecturer.setFirstName(resultSet.getString(2));
            lecturer.setLastName(resultSet.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось найти преподавателя по ID!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return lecturer;
    }

    @Override
    public List<Lecturer> findAll() {
        List<Lecturer> lecturers;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_LECTURERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            lecturers = mapLecturers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось найти всех преподавателей!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return lecturers;
    }

    @Override
    public void update(Lecturer lecturer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LECTURER_INFO)) {
            preparedStatement.setString(1, lecturer.getFirstName());
            preparedStatement.setString(2, lecturer.getLastName());
            preparedStatement.setLong(3, lecturer.getLecturerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить данные преподавателя!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Lecturer lecturer) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_LECTURERS)) {
            preparedStatement.setLong(1, lecturer.getLecturerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось удалить преподавателя!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long countOfEntries() {
        Long entriesCounter;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_LECTURER_ENTRIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            entriesCounter = resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось посчитать преподавателей!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return entriesCounter;
    }

    private static List<Lecturer> mapLecturers(ResultSet resultSet) {
        List<Lecturer> lecturers = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setLecturerId(resultSet.getLong(1));
                lecturer.setFirstName(resultSet.getString(2));
                lecturer.setLastName(resultSet.getString(3));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить преподавателя!", e);
        }
        return lecturers;
    }
}
