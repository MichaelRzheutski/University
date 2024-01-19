package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.SubjectRepository;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.persistence.impl.jdbc.SubjectRepositoryJdbcImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.SubjectService;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;

import java.util.List;

public class SubjectServiceJdbcImpl extends SubjectServiceCommonActions implements SubjectService {
    private final StudentRepository studentRepository = new StudentRepositoryJdbcImpl();
    private final SubjectRepository subjectRepository = new SubjectRepositoryJdbcImpl();
    private final StudentService studentService = new StudentServiceJdbcImpl();

    private List<Student> getStudentsWithSubjects() {
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
