package com.solvd.university.service.impl.proxy;

import com.solvd.university.domain.Student;
import com.solvd.university.service.impl.commonactions.SubjectServiceCA;

import java.util.List;

public class AllSubjectsJdbcProxy extends SubjectServiceCA implements AllSubjectsProxy {
    private final AllSubjectsProxy allSubjectsProxy;
    private List<Student> studentsWithSubjectsCache = null;

    public AllSubjectsJdbcProxy(AllSubjectsProxy allSubjectsProxy) {
        this.allSubjectsProxy = allSubjectsProxy;
    }

    public List<Student> getStudentsWithSubjects() {
        if (studentsWithSubjectsCache == null) {
            studentsWithSubjectsCache = allSubjectsProxy.getStudentsWithSubjects();
        }
        return studentsWithSubjectsCache;
    }

    @Override
    public void printAllSubjects() {
        List<Student> studentsWithSubjects = getStudentsWithSubjects();
        printStudentSubjects(studentsWithSubjects);
    }
}
