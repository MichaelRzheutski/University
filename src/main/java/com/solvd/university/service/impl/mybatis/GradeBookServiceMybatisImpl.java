package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.SubjectRepository;
import com.solvd.university.service.GradeBookService;
import com.solvd.university.service.SubjectServiceSetter;
import com.solvd.university.service.impl.commonactions.GradeBookCA;

import java.util.List;

public class GradeBookServiceMybatisImpl extends GradeBookCA implements GradeBookService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectServiceSetter subjectServiceSetter;

    public GradeBookServiceMybatisImpl(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            SubjectServiceSetter subjectServiceSetter
    ) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.subjectServiceSetter = subjectServiceSetter;
    }

    public List<Student> getStudentsWithSubjects() {
        List<Student> students = studentRepository.findAll();
        List<Subject> subjects = subjectRepository.getAllSubjects();
        return subjectServiceSetter.setSubjectsToStudents(students, subjects);
    }

    @Override
    public void printGradeBook() {
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        printStudentSubjects(studentsWithSubjects);
    }
}
