package com.solvd.university.persistence;

import com.solvd.university.domain.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartments();
}
