package com.solvd.university.persistence.impl;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.StudentRepositoryDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentRepositoryImpl implements StudentRepositoryDao {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_STUDENTS =
            "INSERT INTO students(first_name, last_name, date_of_birth, student_contact_id) values(?, ?, ?, ?);";
    private static final String FIND_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?;";
    private static final String UPDATE_STUDENT_INFO = "UPDATE students SET first_name = ? WHERE student_id = ?;";
    private static final String DELETE_FROM_BUILDINGS = "DELETE FROM students WHERE student_id = ?;";
    private static final String COUNT_STUDENT_ENTRIES = "SELECT COUNT(*) AS students_count FROM students;";
    private static final String GET_STUDENT_AVERAGE_SCORE = "SELECT students.student_id AS `ID Студента`," +
            "students.first_name AS `Имя`, students.last_name AS `Фамилия`," +
            "students.date_of_birth AS `Дата рождения`," + "enrollments.grade AS `Средний балл`" +
            "FROM students LEFT JOIN enrollments ON students.student_id = enrollments.student_id;";

    @Override
    public void create(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_STUDENTS, Statement.RETURN_GENERATED_KEYS)) {

            String enteredStudentFirstName;
            String enteredStudentLastName;
            LocalDate dateOfBirth;
            Long enteredContactId;
            MY_LOGGER.info(ANSI_GREEN + "Введите имя студента:" + ANSI_RESET);

            if (scanner.hasNext()) {
                enteredStudentFirstName = scanner.nextLine();
                preparedStatement.setString(1, enteredStudentFirstName);

                MY_LOGGER.info(ANSI_GREEN + "Введите фамилию студента:" + ANSI_RESET);
                if (scanner.hasNext()) {
                    enteredStudentLastName = scanner.nextLine();
                    preparedStatement.setString(2, enteredStudentLastName);

                    MY_LOGGER.info(ANSI_GREEN + "Введите дату рождения студента в формате (yyyy-mm-dd):" + ANSI_RESET);
                    if (scanner.hasNext()) {
                        dateOfBirth = LocalDate.parse(scanner.nextLine());
                        preparedStatement.setDate(3, Date.valueOf(dateOfBirth));

                        MY_LOGGER.info(ANSI_GREEN + "Введите ID контакта:" + ANSI_RESET);
                        if (scanner.hasNextLong()) {
                            enteredContactId = scanner.nextLong();
                            preparedStatement.setLong(4, enteredContactId);
                        }
                    }

                    preparedStatement.executeUpdate();

                    MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                            + enteredStudentFirstName + " " + enteredStudentLastName + ANSI_RESET + "\n");
                }
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Невозможно создать студента!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Student findById() {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);
        int enteredStudentId;
        Student student = new Student();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENT_BY_ID)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID студента" + ANSI_RESET);

            if (scanner.hasNextInt()) {
                enteredStudentId = scanner.nextInt();
                preparedStatement.setLong(1, enteredStudentId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                student.setStudentId(resultSet.getLong(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не у далось найти студента по ID!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return student;
    }

    public List<Student> findAll() {
        List<Student> students;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_AVERAGE_SCORE)) {
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
    public void update(Student student) {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_INFO)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
            int enteredStudentId;
            String buildingNewName;

            if (scanner.hasNextInt()) {
                enteredStudentId = scanner.nextInt();

                MY_LOGGER.info(ANSI_GREEN + "Введите новое имя студента:" + ANSI_RESET);
                if (scanner.hasNext()) {
                    buildingNewName = scanner.next();

                    preparedStatement.setString(1, buildingNewName);
                    preparedStatement.setLong(2, enteredStudentId);
                    preparedStatement.executeUpdate();
                }

                MY_LOGGER.info(ANSI_GREEN + "Обновлено имя у студента с ID: " + ANSI_YELLOW
                        + enteredStudentId + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить имя студента!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById() {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BUILDINGS)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
            int enteredStudentId;

            if (scanner.hasNextInt()) {
                enteredStudentId = scanner.nextInt();

                preparedStatement.setLong(1, enteredStudentId);
                preparedStatement.executeUpdate();

                MY_LOGGER.info(ANSI_GREEN + "Удалён студент с ID: " + ANSI_YELLOW
                        + enteredStudentId + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

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
            MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                    + entriesCounter + ANSI_RESET + "\n");

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
                student.setDateOfBirth(resultSet.getDate(4));
                student.setAverageScore(resultSet.getDouble(5));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить студента!", e);
        }
        return students;
    }
}
