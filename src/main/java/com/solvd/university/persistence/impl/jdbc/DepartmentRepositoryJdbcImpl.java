package com.solvd.university.persistence.impl.jdbc;

import com.solvd.university.domain.Department;
import com.solvd.university.persistence.ConnectionPool;
import com.solvd.university.persistence.DepartmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryJdbcImpl implements DepartmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String GET_ALL_DEPARTMENTS = "SELECT * FROM departments";

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DEPARTMENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getLong(1));
                department.setDepartmentName(resultSet.getString(2));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось получить все кафедры!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return departments;
    }
}
