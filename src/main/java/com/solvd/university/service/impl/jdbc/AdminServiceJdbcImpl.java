package com.solvd.university.service.impl.jdbc;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCA;

public class AdminServiceJdbcImpl extends AdminServiceCA implements AdminService {

    @Override
    public void authorizeAdmin() {
        setAdminCredentials();
    }
}
