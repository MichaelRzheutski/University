package com.solvd.university.domain.logic;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.StudentRepositoryImpl;

import java.util.List;

import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminLogic {
    public void getAllStudents() {
        List<Student> studentList = new StudentRepositoryImpl().findAll();

        for (Student student : studentList) {
            MY_LOGGER.info(student.getStudentId() + " | " + student.getFirstName() + " | "
                    + student.getLastName() + " | " + student.getDateOfBirth()
            );
        }
    }
}
