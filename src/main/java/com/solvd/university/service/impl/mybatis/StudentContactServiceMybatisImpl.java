package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.StudentContactRepository;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.service.impl.parsers.JacksonStudent;
import com.solvd.university.service.impl.parsers.JaxbStudent;
import com.solvd.university.service.impl.parsers.StaxStudent;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

public class StudentContactServiceMybatisImpl extends StudentContactServiceCommonActions implements StudentContactService {
    private final StaxStudent staxStudent;
    private final JaxbStudent jaxBStudent;
    private final JacksonStudent jacksonStudent;
    private final StudentContactRepository studentContactRepository;

    public StudentContactServiceMybatisImpl(
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
    public void createStudentContact(Student student, XmlConsoleSelectors consoleSelector) {
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
