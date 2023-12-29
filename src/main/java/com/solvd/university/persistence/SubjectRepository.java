package com.solvd.university.persistence;

import com.solvd.university.domain.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> getAllSubjects();
}
