package com.solvd.university.persistence;

import com.solvd.university.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentRepository {
    void create(@Param("student") Student student);

    Student findById(@Param("student") Student student);

    List<Student> findAll();

    void update(@Param("student") Student student);

    void deleteById(Long id);

    Long countOfEntries();
}
