package com.solvd.university.service.impl.jdbc;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCommonActions;

public class AdminServiceJdbcImpl extends AdminServiceCommonActions implements AdminService {

    @Override
    public void authorizeAdmin() {
        setAdminCredentials();
    }
}
