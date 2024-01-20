package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.StudentContactRepository;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCA;
import com.solvd.university.service.impl.parsers.JacksonStudent;
import com.solvd.university.service.impl.parsers.JaxbStudent;
import com.solvd.university.service.impl.parsers.StaxStudent;
import com.solvd.university.util.menus.enums.ParserSelectors;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCA implements StudentContactService {
    private final StaxStudent staxStudent;
    private final JaxbStudent jaxBStudent;
    private final JacksonStudent jacksonStudent;
    private final StudentContactRepository studentContactRepository;

    public StudentContactServiceJdbcImpl(
            StaxStudent staxStudent,
            JaxbStudent jaxBStudent,
            JacksonStudent jacksonStudent,
            StudentContactRepository studentContactRepository
    ) {
        this.staxStudent = staxStudent;
        this.jaxBStudent = jaxBStudent;
        this.jacksonStudent = jacksonStudent;
        this.studentContactRepository = studentContactRepository;
    }

    @Override
    public void createStudentContact(Student student, ParserSelectors consoleSelector) {
        StudentContact studentContact = new StudentContact();
        switch (consoleSelector) {
            case CONSOLE -> studentContact = addContact();
            case STAX -> studentContact = staxStudent.readStudentContactFromXml();
            case JAXB -> studentContact = jaxBStudent.readStudentContactFromJaxb();
            case JACKSON -> studentContact = jacksonStudent.readStudentContactFromJackson();
        }

        studentContact.setStudentId(student.getStudentId());
        studentContactRepository.createStudentContact(studentContact);
    }
}
