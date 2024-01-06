package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCommonActions;

import java.util.List;

public class AdminServiceServiceJdbcImpl extends AdminServiceCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceServiceJdbcImpl().getAdminAccess();
    }

    @Override
    public void printFullStudentInfo() {
        List<Student> studentList = new StudentRepositoryJdbcImpl().findAll();
        new AdminServiceCommonActions().printWholeStudentInfo(studentList);
    }
}
