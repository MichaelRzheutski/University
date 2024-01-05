package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceJdbcImpl extends StudentServiceCommonActions implements StudentService {

    @Override
    public void enrollStudent() {
        new StudentRepositoryJdbcImpl().create(addStudent());
    }

    @Override
    public Student findStudent() {
        Student student = new StudentRepositoryJdbcImpl().findById(getStudentById());
        MY_LOGGER.info(ANSI_GREEN + "Найден студент: " + ANSI_YELLOW + student.getStudentId() + " | " +
                student.getFirstName() + " " + student.getLastName() + ANSI_RESET + "\n");

        return student;
    }

    @Override
    public void editStudentInfo() {
        new StudentRepositoryJdbcImpl().update(editInfo());
    }

    @Override
    public void expelStudentById() {
        new StudentRepositoryJdbcImpl().deleteById(deleteStudent());
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + new StudentRepositoryJdbcImpl().countOfEntries() + ANSI_RESET + "\n");
    }
}
