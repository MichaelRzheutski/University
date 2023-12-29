package com.solvd.university.persistence;

import com.solvd.university.domain.Subject;

import java.util.List;

public interface SubjectRepositoryDao {
    List<Subject> getAllSubjects();
}
