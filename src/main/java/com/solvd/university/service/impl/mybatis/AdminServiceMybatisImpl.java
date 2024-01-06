package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCommonActions;

import java.util.List;

public class AdminServiceMybatisImpl extends AdminServiceCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceMybatisImpl().getAdminAccess();
    }

    @Override
    public void printFullStudentInfo() {
        List<Student> studentList = new StudentRepositoryMybatisImpl().findAll();
        new AdminServiceCommonActions().printWholeStudentInfo(studentList);
    }
}
