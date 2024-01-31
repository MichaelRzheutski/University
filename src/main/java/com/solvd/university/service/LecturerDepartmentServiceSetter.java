package com.solvd.university.service;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Lecturer;

import java.util.List;

public interface LecturerDepartmentServiceSetter {
    List<Lecturer> setDepartmentsToLecturers(List<Lecturer> lecturerList, List<Department> departmentList);

}
