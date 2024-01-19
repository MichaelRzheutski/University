package com.solvd.university.service.impl.proxy;

import com.solvd.university.domain.Student;
import com.solvd.university.service.impl.commonactions.SubjectServiceCommonActions;
import com.solvd.university.service.impl.mybatis.SubjectServiceMybatisImpl;

import java.util.List;

public class AllSubjectsMybatisProxy extends SubjectServiceCommonActions implements AllSubjectsProxy {
    private final AllSubjectsProxy allSubjectsProxy = new SubjectServiceMybatisImpl();
    private List<Student> studentsWithSubjectsCache = null;

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
