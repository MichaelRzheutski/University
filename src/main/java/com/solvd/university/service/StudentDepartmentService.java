package com.solvd.university.service;

import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentDepartmentService {
    List<Student> getStudentsWithDepartments();
}
