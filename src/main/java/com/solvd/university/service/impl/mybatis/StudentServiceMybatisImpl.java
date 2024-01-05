package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceMybatisImpl extends StudentServiceCommonActions implements StudentService {
    @Override
    public void enrollStudent() {
        new StudentRepositoryMybatisImpl().create(addStudent());
    }

    @Override
    public Student findStudent() {
        Student student = new StudentRepositoryMybatisImpl().findById(getStudentById());
        MY_LOGGER.info(ANSI_GREEN + "Найден студент: " + ANSI_YELLOW + student.getStudentId() + " | " +
                student.getFirstName() + " " + student.getLastName() + ANSI_RESET);

        return student;
    }

    @Override
    public void editStudentInfo() {
        new StudentRepositoryMybatisImpl().update(editInfo());
    }

    @Override
    public void expelStudentById() {
        new StudentRepositoryMybatisImpl().deleteById(deleteStudent());
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + new StudentRepositoryMybatisImpl().countOfEntries() + ANSI_RESET + "\n");
    }
}
