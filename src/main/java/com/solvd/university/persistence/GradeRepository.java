package com.solvd.university.persistence;

import com.solvd.university.domain.Grade;

import java.util.List;

public interface GradeRepository {
    void create(Grade grade);

    Grade findById();

    List<Grade> findAll();

    void update(Grade grade);

    void deleteById();

    Long countOfEntries();
}
