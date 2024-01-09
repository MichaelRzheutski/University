package com.solvd.university.persistence;

import com.solvd.university.domain.StudentContact;

public interface StudentContactRepository {
    void create(StudentContact studentContact);
}
