package com.solvd.university.service.impl.jdbc;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCommonActions;

public class AdminServiceServiceJdbcImpl extends AdminServiceCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceServiceJdbcImpl().getAdminAccess();
    }

    @Override
    public void printFullStudentInfo() {
        new AdminServiceCommonActions().printWholeStudentInfo();
    }
}
