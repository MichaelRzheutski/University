package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.jdbc.DepartmentRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.service.DepartmentService;
import com.solvd.university.service.impl.commonactions.DepartmentServiceCommonActions;

import java.util.List;

public class DepartmentServiceJdbcImpl extends DepartmentServiceCommonActions implements DepartmentService {
    @Override
    public List<Student> getStudentsWithDepartments() {
        List<Student> students = new StudentRepositoryJdbcImpl().findAll();
        List<Department> departments = new DepartmentRepositoryJdbcImpl().getAllDepartments();

        return new DepartmentServiceCommonActions().setDepartmentsToStudents(students, departments);
    }
}
