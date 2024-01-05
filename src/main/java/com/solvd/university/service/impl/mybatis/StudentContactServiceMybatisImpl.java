package com.solvd.university.service.impl.mybatis;

import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactCommonActions;

public class StudentContactServiceMybatisImpl extends StudentContactCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        new StudentContactRepositoryMybatisImpl().create(addContact());
    }
}
