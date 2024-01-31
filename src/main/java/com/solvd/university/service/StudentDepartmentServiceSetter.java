package com.solvd.university.service;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;

import java.util.List;

public interface StudentDepartmentServiceSetter {
    List<Student> setDepartmentsToStudents(List<Student> studentList, List<Department> departmentList);
}
