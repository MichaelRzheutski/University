package com.solvd.university.persistence;

import com.solvd.university.domain.LecturerContact;

import java.util.List;

public interface LecturerContactRepository {
    void create(LecturerContact lecturerContact);

    LecturerContact findById();

    List<LecturerContact> findAll();

    void update(LecturerContact lecturerContact);

    void deleteById();

    Long countOfEntries();
}
