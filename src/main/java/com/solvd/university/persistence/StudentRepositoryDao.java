package com.solvd.university.persistence;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;

import java.util.List;

public interface StudentRepositoryDao {
    void create(Student student);

    Student findById();

    List<Student> findAll();

    void update(Student student);

    void deleteById();

    Long countOfEntries();
}
