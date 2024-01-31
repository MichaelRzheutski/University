package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.DepartmentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.DepartmentService;
import com.solvd.university.service.impl.commonactions.DepartmentServiceCommonActions;

import java.util.List;

public class DepartmentServiceMybatisImpl extends DepartmentServiceCommonActions implements DepartmentService {
    @Override
    public List<Student> getStudentsWithDepartments() {
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        List<Department> departments = new DepartmentRepositoryMybatisImpl().getAllDepartments();

        return new DepartmentServiceCommonActions().setDepartmentsToStudents(students, departments);
    }
}
