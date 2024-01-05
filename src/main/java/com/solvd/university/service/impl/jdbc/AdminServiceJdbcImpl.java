package com.solvd.university.service.impl.jdbc;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminCommonActions;

public class AdminServiceJdbcImpl extends AdminCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceJdbcImpl().getAdminAccess();
    }

    @Override
    public void printFullStudentInfo() {
        new AdminCommonActions().printWholeStudentInfo();
    }
}
