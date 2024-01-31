package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceMybatisImpl extends StudentServiceCommonActions implements StudentService {
    @Override
    public void printFullStudentInfo() {
        List<Student> students = new StudentRepositoryMybatisImpl().findAll();
        printWholeStudentInfo(students);
    }

    @Override
    public void enrollStudent() {
        Student studentToCreate = addStudent();
        new StudentRepositoryMybatisImpl().create(studentToCreate);
        MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                + studentToCreate.getFirstName() + " " + studentToCreate.getLastName() + ANSI_RESET + "\n");
    }

    @Override
    public Student findStudent() {
        Student studentToFound = getStudentById();
        Student foundStudent = new StudentRepositoryMybatisImpl().findById(studentToFound);
        MY_LOGGER.info(ANSI_GREEN + "Найден студент: " + ANSI_YELLOW + foundStudent.getStudentId() + " | " +
                foundStudent.getFirstName() + " " + foundStudent.getLastName() + ANSI_RESET + "\n");

        return foundStudent;
    }

    @Override
    public void editStudentInfo() {
        Student studentToUpdateInfo = editInfo();
        new StudentRepositoryMybatisImpl().update(studentToUpdateInfo);
        MY_LOGGER.info(ANSI_GREEN + "Обновлено имя у студента с ID: " + ANSI_YELLOW
                + studentToUpdateInfo.getStudentId() + ANSI_RESET + "\n");
    }

    @Override
    public void expelStudentById() {
        Long studentToDelete = deleteStudent();
        new StudentRepositoryMybatisImpl().deleteById(studentToDelete);
        MY_LOGGER.info(ANSI_GREEN + "Удалён студент с ID: " + ANSI_YELLOW
                + studentToDelete + ANSI_RESET + "\n");
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + new StudentRepositoryMybatisImpl().countOfEntries() + ANSI_RESET + "\n");
    }
}
