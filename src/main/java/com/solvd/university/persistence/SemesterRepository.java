package com.solvd.university.persistence;

import com.solvd.university.domain.Semester;

import java.util.List;

public interface SemesterRepository {
    void create(Semester semester);

    Semester findById();

    List<Semester> findAll();

    void update(Semester semester);

    void deleteById();

    Long countOfEntries();
}
