package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.SubjectRepository;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.SubjectCAService;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;
import com.solvd.university.service.impl.proxy.AllSubjectsProxy;

import java.util.List;

public class SubjectServiceMybatisImpl extends SubjectServiceCommonActions implements SubjectService, AllSubjectsProxy {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentService studentService;
    private final SubjectCAService subjectCAService;

    public SubjectServiceMybatisImpl(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            StudentService studentService,
            SubjectCAService subjectCAService
    ) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
        this.subjectCAService = subjectCAService;
    }

    public List<Student> getStudentsWithSubjects() {
        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.getAllSubjects();
        return subjectCAService.setSubjectsToStudents(students, subjects);
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