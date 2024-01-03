package com.solvd.university.persistence;

import com.solvd.university.domain.StudentContact;

import java.util.List;

public interface StudentContactRepository {
    void create(StudentContact studentContact);

    void findById();

    List<StudentContact> findAll();

    void update(StudentContact studentContact);

    void deleteById();

    Long countOfEntries();
}
