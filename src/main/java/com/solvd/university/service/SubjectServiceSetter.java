package com.solvd.university.service;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;

import java.util.List;

public interface SubjectServiceSetter {
    List<Student> setSubjectsToStudents(List<Student> studentList, List<Subject> subjectList);
}
