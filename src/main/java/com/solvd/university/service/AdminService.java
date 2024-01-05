package com.solvd.university.service;

import com.solvd.university.domain.Admin;

public interface AdminService {
    void authorize(Admin admin);

    void printAllStudents();
}
