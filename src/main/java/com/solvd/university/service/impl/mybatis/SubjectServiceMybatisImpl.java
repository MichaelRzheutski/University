package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.SubjectRepository;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.SubjectServiceSetter;
import com.solvd.university.service.impl.commonactions.SubjectServiceCA;
import com.solvd.university.service.impl.proxy.AllSubjectsProxy;

import java.util.List;

public class SubjectServiceMybatisImpl extends SubjectServiceCA implements SubjectService, AllSubjectsProxy {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentService studentService;
    private final SubjectServiceSetter subjectServiceSetter;

    public SubjectServiceMybatisImpl(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            StudentService studentService,
            SubjectServiceSetter subjectServiceSetter
    ) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
        this.subjectServiceSetter = subjectServiceSetter;
    }

    public List<Student> getStudentsWithSubjects() {
        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.getAllSubjects();
        return subjectServiceSetter.setSubjectsToStudents(students, subjects);
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