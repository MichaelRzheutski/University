package com.solvd.university.service.impl.mybatis;

import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;

public class StudentContactServiceMybatisImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        new StudentContactRepositoryMybatisImpl().create(addContact());
    }
}
