package com.solvd.university.persistence;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import org.apache.ibatis.annotations.Param;

public interface StudentContactRepository {
    void createStudentContact(@Param("student") Student student,
                              @Param("studentContact") StudentContact studentContact);
}