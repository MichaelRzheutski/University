package com.solvd.university.persistence;

import com.solvd.university.domain.Enrollment;

import java.util.List;

public interface EnrollmentRepository {
    void create(Enrollment enrollment);

    Enrollment findById();

    List<Enrollment> findAll();

    void update(Enrollment enrollment);

    void deleteById();

    Long countOfEntries();
}
