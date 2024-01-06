package com.solvd.university.persistence;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();

    void create(Student student);

    Student findById(Student student);

    void update(Student student);

    void deleteById(Long id);

    Long countOfEntries();
}
