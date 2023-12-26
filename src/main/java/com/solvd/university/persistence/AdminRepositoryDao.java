package com.solvd.university.persistence;

import com.solvd.university.domain.Admin;

public interface AdminRepositoryDao {
    void authorizeAdmin(Admin admin);
}
