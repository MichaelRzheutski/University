package com.solvd.university.service.impl.jdbc;

import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {
    @Override
    public void createStudentContact() {
        new StudentContactRepositoryJdbcImpl().create(addContact());
    }
}
