package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.StudentContactRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.StudentDepartmentService;
import com.solvd.university.service.StudentService;
import com.solvd.university.service.impl.commonactions.StudentServiceCommonActions;
import com.solvd.university.service.impl.parsers.JacksonStudent;
import com.solvd.university.service.impl.parsers.JaxbStudent;
import com.solvd.university.service.impl.parsers.StaxStudent;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceMybatisImpl extends StudentServiceCommonActions implements StudentService {
    private final StudentDepartmentService studentDepartmentService;
    private final StudentContactRepository studentContactRepository;
    private final StudentRepository studentRepository;
    private final StudentContactService studentContactService;

    public StudentServiceMybatisImpl(
            StaxStudent staxStudent,
            JaxbStudent jaxBStudent,
            JacksonStudent jacksonStudent,
            StudentDepartmentService studentDepartmentService,
            StudentContactRepository studentContactRepository,
            StudentRepository studentRepository,
            StudentContactService studentContactService
    ) {
        super(staxStudent, jaxBStudent, jacksonStudent);
        this.studentDepartmentService = studentDepartmentService;
        this.studentContactRepository = studentContactRepository;
        this.studentRepository = studentRepository;
        this.studentContactService = studentContactService;
    }

    private List<Student> getStudentsWithContacts() {
        List<Student> studentList = studentDepartmentService.getStudentsWithDepartments();
        List<StudentContact> contactList = studentContactRepository.getAllStudentContacts();
        return setStudentContactData(studentList, contactList);
    }

    @Override
    public void printFullStudentInfo() {
        List<Student> studentsWithContacts = getStudentsWithContacts();
        printWholeStudentInfo(studentsWithContacts);
    }

    @Override
    public void enrollStudent(XmlConsoleSelectors xmlConsoleSelector) {
        Student studentToEnroll = addStudent(xmlConsoleSelector);
        studentRepository.create(studentToEnroll);
        studentContactService.createStudentContact(studentToEnroll, xmlConsoleSelector);
        MY_LOGGER.info(ANSI_GREEN + "\n" + "Студент был добавлен в базу:" + "\n" +
                "Id" + " | " +
                "Имя и фамилия" + " | " +
                "Дата рождения" + " | " + ANSI_YELLOW + "\n" +
                studentToEnroll.getStudentId() + " | " +
                studentToEnroll.getFirstName() + " " +
                studentToEnroll.getLastName() + " | " +
                studentToEnroll.getDateOfBirth() + ANSI_RESET + "\n"
        );
    }

    @Override
    public Student findStudent() {
        Student studentToFound = getStudentById();
        Student foundStudent = studentRepository.findById(studentToFound);
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
        studentRepository.update(studentToUpdateInfo);
        MY_LOGGER.info(ANSI_GREEN + "Обновлена информация у студента с ID: " + ANSI_YELLOW
                + studentToUpdateInfo.getStudentId() + ANSI_RESET + "\n");
    }

    @Override
    public void expelStudentById() {
        Student student = getStudentById();
        Student studentToDelete = studentRepository.findById(student);
        studentRepository.deleteById(studentToDelete);
        MY_LOGGER.info(ANSI_GREEN + "Удалён студент(ка): " + ANSI_YELLOW +
                studentToDelete.getStudentId() + " | " +
                studentToDelete.getFirstName() + " " +
                studentToDelete.getLastName() + ANSI_RESET + "\n"
        );
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество студентов: " + ANSI_YELLOW
                + studentRepository.countOfEntries() + ANSI_RESET + "\n");
    }
}
