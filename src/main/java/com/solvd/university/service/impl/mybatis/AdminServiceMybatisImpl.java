package com.solvd.university.service.impl.mybatis;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCA;

public class AdminServiceMybatisImpl extends AdminServiceCA implements AdminService {
    @Override
    public void authorizeAdmin() {
        setAdminCredentials();
    }
}
