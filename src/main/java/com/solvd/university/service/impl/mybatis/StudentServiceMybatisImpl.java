package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentCommonActions;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceMybatisImpl extends StudentCommonActions implements StudentService {
    @Override
    public void enrollStudent() {
        new StudentRepositoryMybatisImpl().create(addStudent());
    }

    @Override
    public void findStudent() {
        Student student = new StudentRepositoryMybatisImpl().findById(getStudentById());
        MY_LOGGER.info(ANSI_GREEN + "Найден студент: " + ANSI_YELLOW + student.getStudentId() + " | " +
                student.getFirstName() + " " + student.getLastName() + ANSI_RESET);
    }

    @Override
    public void editStudentInfo() {
        new StudentRepositoryMybatisImpl().update(editInfo());
    }

    @Override
    public List<Student> printAllSubjects() {
        return null;
    }

    @Override
    public Student getStudentAllSubjects() {
        return null;
    }

    @Override
    public Student showStudentPerformance() {
        return null;
    }

    @Override
    public String takeExam() {
        return null;
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
