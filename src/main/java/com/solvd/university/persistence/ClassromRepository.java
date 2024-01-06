package com.solvd.university.persistence;

import com.solvd.university.domain.Classroom;

import java.util.List;

public interface ClassromRepository {
    void create(Classroom classroom);

    Classroom findById();

    List<Classroom> findAll();

    void update(Classroom student);

    void deleteById();

    Long countOfEntries();
}
