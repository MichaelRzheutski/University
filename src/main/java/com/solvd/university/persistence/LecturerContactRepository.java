package com.solvd.university.persistence;

import com.solvd.university.domain.LecturerContact;

import java.util.List;

public interface LecturerContactRepository {
    void createLecturerContact(LecturerContact lecturerContact);

    List<LecturerContact> getAllLecturerContacts();
}
