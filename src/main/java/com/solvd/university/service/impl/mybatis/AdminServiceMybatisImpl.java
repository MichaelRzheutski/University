package com.solvd.university.service.impl.mybatis;

import com.solvd.university.service.AdminService;
import com.solvd.university.service.impl.commonactions.AdminServiceCommonActions;

public class AdminServiceMybatisImpl extends AdminServiceCommonActions implements AdminService {
    @Override
    public void authorizeAdmin() {
        new AdminServiceMybatisImpl().getAdminAccess();
    }
}
