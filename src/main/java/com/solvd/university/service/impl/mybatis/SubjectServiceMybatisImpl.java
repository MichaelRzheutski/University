package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.SubjectRepository;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.SubjectRepositoryMybatisImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;
import com.solvd.university.service.impl.proxy.AllSubjectsProxy;
import com.solvd.university.util.parsers.JacksonServiceOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxServiceOperations;

import java.util.List;

public class SubjectServiceMybatisImpl extends SubjectServiceCommonActions implements SubjectService, AllSubjectsProxy {
    private final StudentRepository studentRepository = new StudentRepositoryMybatisImpl();
    private final SubjectRepository subjectRepository = new SubjectRepositoryMybatisImpl();
    private final StudentService studentService = new StudentServiceMybatisImpl(
            new StaxServiceOperations(), new JaxbOperations(), new JacksonServiceOperations()
    );

    public List<Student> getStudentsWithSubjects() {
        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.getAllSubjects();
        return new SubjectServiceCommonActions().setSubjectsToStudents(students, subjects);
    }

    @Override
    public void printAllSubjects() {
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        printStudentSubjects(studentsWithSubjects);
    }

    @Override
    public void getStudentAllSubjects() {
        Student foundStudent = studentService.findStudent();
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        showStudentAllSubjects(studentsWithSubjects, foundStudent);
    }

    @Override
    public void showStudentPerformance() {
        Student foundStudent = studentService.findStudent();
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        showStudentResults(studentsWithSubjects, foundStudent);
    }

    @Override
    public void takeExam() {
        Student foundStudent = studentService.findStudent();
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        passExam(studentsWithSubjects, foundStudent);
    }
}