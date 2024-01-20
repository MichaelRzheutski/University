package com.solvd.university.service;

import com.solvd.university.domain.Lecturer;

import java.util.List;

public interface LecturerDepartmentService {
    List<Lecturer> getLecturersWithDepartments();
}
