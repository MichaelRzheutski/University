package com.solvd.university.persistence;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentRepository {
    void create(Student student);

    Student findById();

    List<Student> findAll();

    void update(Student student);

    void deleteById();

    Long countOfEntries();
}
