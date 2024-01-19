package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.service.JacksonService;
import com.solvd.university.service.JaxBService;
import com.solvd.university.service.StaxService;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.parsers.JacksonServiceOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxServiceOperations;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceCommonActions {
    private final StaxService staxService = new StaxServiceOperations();
    private final JaxBService jaxBService = new JaxbOperations();
    private final JacksonService jacksonService = new JacksonServiceOperations();

    protected void printWholeStudentInfo(List<Student> studentsWithContacts) {
        MY_LOGGER.info("\n" + ANSI_GREEN +
                "Id" + " | " +
                "Имя и Фамилия" + " | " +
                "Дата рождения" + " | " +
                "Кафедра" + " | " +
                "Курс" + " | " +
                "Средний балл" + " | " +
                "Телефон" + " | " +
                "Email" + ANSI_RESET
        );
        for (Student student : studentsWithContacts) {
            if (student.getStudentContact() != null) {
                MY_LOGGER.info("\n" + student.getStudentId() + " | " + ANSI_YELLOW +
                        student.getFirstName() + " " +
                        student.getLastName() + " | " +
                        student.getDateOfBirth() + " | " +
                        student.getDepartment().getDepartmentName() + " | " +
                        student.getDepartment().getCourse() + " курс | " +
                        student.getAverageScore() + " | " +
                        student.getStudentContact().getPhone() + " | " +
                        student.getStudentContact().getEmail() + ANSI_RESET
                );
            } else {
                MY_LOGGER.info("\n" + student.getStudentId() + " | " + ANSI_YELLOW +
                        student.getFirstName() + " " +
                        student.getLastName() + " | " +
                        student.getDateOfBirth() + " | " +
                        student.getDepartment().getDepartmentName() + " | " +
                        student.getDepartment().getCourse() + " курс | " +
                        student.getAverageScore() + " | " +
                        "Телефон не указан" + " | " +
                        "Email не указан" + ANSI_RESET
                );
            }
        }

        System.out.println();
    }

    protected List<Student> setStudentContactData(List<Student> studentList, List<StudentContact> contactList) {
        for (Student student : studentList) {
            for (StudentContact studentContact : contactList) {
                if (studentContact.getStudentId().equals(student.getStudentId())) {
                    student.setStudentContact(studentContact);
                }
            }
        }

        return studentList;
    }

    protected Student addStudent(XmlConsoleSelectors xmlConsoleSelector) {
        Student studentToCreate = new Student();
        switch (xmlConsoleSelector) {
            case CONSOLE -> studentToCreate = collectStudentData();
            case STAX -> studentToCreate = staxService.readStudentFromXml();
            case JAXB -> studentToCreate = jaxBService.readStudentFromJaxb();
            case JACKSON -> studentToCreate = jacksonService.readStudentFromJackson();
        }
        return studentToCreate;
    }

    protected Student getStudentById() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            student.setStudentId(scanner.nextLong());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }
        return student;
    }

    protected Student editInfo() {
        Scanner scanner = new Scanner(System.in);
        Student student;
        Long enteredStudentId = null;

        MY_LOGGER.info(ANSI_GREEN + "Введите ID студента:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            enteredStudentId = scanner.nextLong();
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        student = collectStudentData();
        student.setStudentId(enteredStudentId);

        return student;
    }

    private Student collectStudentData() {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        MY_LOGGER.info(ANSI_GREEN + "Введите имя студента:" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setFirstName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите фамилию студента:" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setLastName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите дату рождения студента в формате (yyyy-mm-dd):" + ANSI_RESET);
        if (scanner.hasNext()) {
            student.setDateOfBirth(LocalDate.parse(scanner.next()));
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return student;
    }
}
