package com.solvd.university.persistence;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentRepositoryDao {
    void create(Student student);

    void findById();

    List<Student> findAll();

    void update(Student student);

    void deleteById();

    Long countOfEntries();
}
