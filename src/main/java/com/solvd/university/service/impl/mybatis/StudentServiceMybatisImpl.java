package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.impl.mybatis.StudentContactRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.parsers.JacksonOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxOperations;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceMybatisImpl extends StudentServiceCommonActions implements StudentService {
    @Override
    public void printFullStudentInfo() {
        List<Student> studentList = new DepartmentServiceMybatisImpl().getStudentsWithDepartments();
        List<StudentContact> contactList = new StudentContactRepositoryMybatisImpl().getAllStudentContacts();
        printWholeStudentInfo(studentList, contactList);
    }

    @Override
    public void enrollStudent(XmlConsoleSelectors xmlConsoleSelector) {
        Student studentToCreate = new Student();
        switch (xmlConsoleSelector) {
            case CONSOLE -> studentToCreate = addStudent();
            case STAX -> studentToCreate = new StaxOperations().readStudentFromXml();
            case JAXB -> studentToCreate = new JaxbOperations().readStudentFromJaxb();
            case JACKSON -> studentToCreate = new JacksonOperations().readStudentFromJackson();
        }

        new StudentRepositoryMybatisImpl().create(studentToCreate);
        new StudentContactServiceMybatisImpl().createStudentContact(studentToCreate, xmlConsoleSelector);

        MY_LOGGER.info(ANSI_GREEN + "\n" + "Студент был добавлен в базу:" + "\n" +
                "Id" + " | " +
                "Имя и фамилия" + " | " +
                "Дата рождения" + " | " + ANSI_YELLOW + "\n" +
                studentToCreate.getStudentId() + " | " +
                studentToCreate.getFirstName() + " " +
                studentToCreate.getLastName() + " | " +
                studentToCreate.getDateOfBirth() + ANSI_RESET + "\n"
        );
    }

    @Override
    public Student findStudent() {
        Student studentToFound = getStudentById();
        Student foundStudent = new StudentRepositoryMybatisImpl().findById(studentToFound);
        MY_LOGGER.info(ANSI_GREEN + "Найден студент(ка): " + ANSI_YELLOW +
                foundStudent.getStudentId() + " | " +
                foundStudent.getFirstName() + " " +
                foundStudent.getLastName() + ANSI_RESET + "\n"
        );

        return foundStudent;
    }

    @Override
    public void editStudentInfo() {
        Student studentToUpdateInfo = editInfo();
        new StudentRepositoryMybatisImpl().update(studentToUpdateInfo);
        MY_LOGGER.info(ANSI_GREEN + "Обновлена информация у студента с ID: " + ANSI_YELLOW
                + studentToUpdateInfo.getStudentId() + ANSI_RESET + "\n");
    }

    @Override
    public void expelStudentById() {
        Student student = getStudentById();
        Student studentToDelete = new StudentRepositoryMybatisImpl().findById(student);
        new StudentRepositoryMybatisImpl().deleteById(studentToDelete);
        MY_LOGGER.info(ANSI_GREEN + "Удалён студент(ка): " + ANSI_YELLOW +
                studentToDelete.getStudentId() + " | " +
                studentToDelete.getFirstName() + " " +
                studentToDelete.getLastName() + ANSI_RESET + "\n"
        );
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + new StudentRepositoryMybatisImpl().countOfEntries() + ANSI_RESET + "\n");
    }
}
