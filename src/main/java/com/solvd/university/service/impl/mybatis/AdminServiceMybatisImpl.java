package com.solvd.university.service.impl.mybatis;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminCommonActions;

public class AdminServiceMybatisImpl extends AdminCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceMybatisImpl().getAdminAccess();
    }

    @Override
    public void printFullStudentInfo() {
        new AdminCommonActions().printWholeStudentInfo();
    }
}
