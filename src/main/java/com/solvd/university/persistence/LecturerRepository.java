package com.solvd.university.persistence;

import com.solvd.university.domain.Lecturer;

import java.util.List;

public interface LecturerRepository {
    List<Lecturer> findAll();

    void create(Lecturer lecturer);

    Lecturer findById(Lecturer lecturer);

    void update(Lecturer lecturer);

    void deleteById(Lecturer lecturer);

    Long countOfEntries();
}
