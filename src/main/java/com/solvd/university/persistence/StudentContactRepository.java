package com.solvd.university.persistence;

import com.solvd.university.domain.StudentContact;

import java.util.List;

public interface StudentContactRepository {
    void createStudentContact(StudentContact studentContact);

    List<StudentContact> getAllStudentContacts();
}