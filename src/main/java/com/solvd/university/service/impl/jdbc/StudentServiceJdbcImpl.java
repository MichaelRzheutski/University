package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.impl.jdbc.StudentRepositoryJdbcImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceJdbcImpl extends StudentServiceCommonActions implements StudentService {
    @Override
    public void printFullStudentInfo() {
        List<Student> studentList = new DepartmentServiceJdbcImpl().getStudentsWithDepartments();
        printWholeStudentInfo(studentList);
    }

    @Override
    public void enrollStudent() {
        Student studentToCreate = addStudent();
        new StudentRepositoryJdbcImpl().create(studentToCreate);
        MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                + studentToCreate.getFirstName() + " " + studentToCreate.getLastName() + ANSI_RESET + "\n");
    }

    @Override
    public void enrollStudentStax() {
        Student studentToCreate = new StaxOperations().readStudentFromXml();
        new StudentRepositoryJdbcImpl().create(studentToCreate);
        MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                + studentToCreate.getFirstName() + " " + studentToCreate.getLastName() + ANSI_RESET + "\n");
    }

    @Override
    public void enrollStudentJaxb() {
        Student studentToCreate = new JaxbOperations().readStudentFromJaxb();
        new StudentRepositoryJdbcImpl().create(studentToCreate);
        MY_LOGGER.info(ANSI_GREEN + "Студент был добавлен в базу: " + ANSI_YELLOW
                + studentToCreate.getFirstName() + " " + studentToCreate.getLastName() + ANSI_RESET + "\n");
    }

    @Override
    public Student findStudent() {
        Student foundByIdStudent = getStudentById();
        new StudentRepositoryJdbcImpl().findById(foundByIdStudent);
        MY_LOGGER.info(ANSI_GREEN + "Найден студент: " + ANSI_YELLOW + foundByIdStudent.getStudentId() + " | " +
                foundByIdStudent.getFirstName() + " " + foundByIdStudent.getLastName() + ANSI_RESET + "\n");

        return foundByIdStudent;
    }

    @Override
    public void editStudentInfo() {
        Student studentToUpdateInfo = editInfo();
        new StudentRepositoryJdbcImpl().update(studentToUpdateInfo);
        MY_LOGGER.info(ANSI_GREEN + "Обновлено имя у студента с ID: " + ANSI_YELLOW
                + studentToUpdateInfo.getStudentId() + ANSI_RESET + "\n");
    }

    @Override
    public void expelStudentById() {
        Long studentToDelete = deleteStudent();
        new StudentRepositoryJdbcImpl().deleteById(studentToDelete);
        MY_LOGGER.info(ANSI_GREEN + "Удалён студент с ID: " + ANSI_YELLOW
                + studentToDelete + ANSI_RESET + "\n");
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + new StudentRepositoryJdbcImpl().countOfEntries() + ANSI_RESET + "\n");
    }
}
