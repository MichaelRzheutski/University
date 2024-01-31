package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.SubjectRepositoryMybatisImpl;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;

import java.util.List;

public class SubjectServiceMybatisImpl extends SubjectServiceCommonActions implements SubjectService {

    @Override
    public void printAllSubjects() {
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryMybatisImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        printStudentSubjects(studentsWithSubjects);
    }

    @Override
    public void getStudentAllSubjects() {
        Student foundStudent = new StudentServiceMybatisImpl().findStudent();
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryMybatisImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        showStudentAllSubjects(studentsWithSubjects, foundStudent);
    }

    @Override
    public void showStudentPerformance() {
        Student foundStudent = new StudentServiceMybatisImpl().findStudent();
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryMybatisImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        showStudentResults(studentsWithSubjects, foundStudent);
    }

    @Override
    public void takeExam() {
        Student foundStudent = new StudentServiceMybatisImpl().findStudent();
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        List<Subject> subjects = new SubjectRepositoryMybatisImpl().getAllSubjects();
        List<Student> studentsWithSubjects =
                new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
        passExam(studentsWithSubjects, foundStudent);
    }
}