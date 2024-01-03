package com.solvd.university.persistence;

import com.solvd.university.domain.Admin;

public interface AdminRepository {
    void authorizeAdmin(Admin admin);
}
