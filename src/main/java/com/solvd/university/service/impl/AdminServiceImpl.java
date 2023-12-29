package com.solvd.university.service.impl;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.StudentRepositoryDaoImpl;
import com.solvd.university.service.AdminService;

import java.util.List;

import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new StudentRepositoryDaoImpl().findAll();

        for (Student student : studentList) {
            MY_LOGGER.info(student.getStudentId() + " | " + student.getFirstName() + " | "
                    + student.getLastName() + " | " + student.getDateOfBirth()
            );
        }

        return studentList;
    }
}
