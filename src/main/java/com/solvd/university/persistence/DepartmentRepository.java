package com.solvd.university.persistence;

import com.solvd.university.domain.Department;

import java.util.List;

public interface DepartmentRepository {
    void create(Department department);

    Department findById();

    List<Department> findAll();

    void update(Department department);

    void deleteById();

    Long countOfEntries();
}
