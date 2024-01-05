package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;

import java.util.List;

public class SubjectServiceJdbcImpl extends SubjectServiceCommonActions implements SubjectService {
    @Override
    public void printAllSubjects() {
        List<Student> students = new StudentRepositoryJdbcImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryJdbcImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        printStudentSubjects(studentsWithSubjects);
    }

    @Override
    public void getStudentAllSubjects() {
        Student foundStudent = new StudentServiceJdbcImpl().findStudent();
        List<Student> students = new StudentRepositoryJdbcImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryJdbcImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        showStudentAllSubjects(studentsWithSubjects, foundStudent);
    }

    @Override
    public void showStudentPerformance() {
        Student foundStudent = new StudentServiceJdbcImpl().findStudent();
        List<Student> students = new StudentRepositoryJdbcImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryJdbcImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        showStudentResults(studentsWithSubjects, foundStudent);
    }

    @Override
    public void takeExam() {
        Student foundStudent = new StudentServiceJdbcImpl().findStudent();
        List<Student> students = new StudentRepositoryJdbcImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryJdbcImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        passExam(studentsWithSubjects, foundStudent);
    }

}
