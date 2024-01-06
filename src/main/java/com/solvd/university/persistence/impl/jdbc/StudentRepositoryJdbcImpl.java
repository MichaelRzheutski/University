package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryJdbcImpl implements StudentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_STUDENTS =
            "INSERT INTO students (first_name, last_name, date_of_birth, student_contact_id) VALUES (?, ?, ?, ?);";
    private static final String FIND_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?;";
    private static final String UPDATE_STUDENT_INFO = "UPDATE students SET first_name = ? WHERE student_id = ?;";
    private static final String DELETE_FROM_BUILDINGS = "DELETE FROM students WHERE student_id = ?;";
    private static final String COUNT_STUDENT_ENTRIES = "SELECT COUNT(*) AS students_count FROM students;";
    private static final String GET_ALL_STUDENTS = "SELECT * FROM students " +
            "LEFT JOIN enrollments ON students.student_id = enrollments.student_id";

    @Override
    public List<Student> findAll() {
        List<Student> students;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            students = mapStudents(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось найти всех студентов!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return students;
    }

    @Override
    public void create(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_STUDENTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setDate(3, Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setLong(4, student.getStudentContactId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно создать студента!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Student findById(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENT_BY_ID)) {
            preparedStatement.setLong(1, student.getStudentId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student.setFirstName(resultSet.getString(2));
            student.setLastName(resultSet.getString(3));
        } catch (SQLException e) {
            throw new RuntimeException("Не у далось найти студента по ID!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return student;
    }

    @Override
    public void update(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_INFO)) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setLong(2, student.getStudentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить имя студента!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BUILDINGS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось удалить студента!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long countOfEntries() {
        Long entriesCounter;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_STUDENT_ENTRIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            entriesCounter = resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось посчитать студентов!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return entriesCounter;
    }

    private static List<Student> mapStudents(ResultSet resultSet) {
        List<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getLong(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
                student.setDateOfBirth(resultSet.getDate(4).toLocalDate());
                student.setAverageScore(resultSet.getDouble(5));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить студента!", e);
        }
        return students;
    }
}
