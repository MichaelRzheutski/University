package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.StudentContactRepository;
import com.solvd.university.persistence.impl.jdbc.StudentContactRepositoryJdbcImpl;
import com.solvd.university.service.JacksonService;
import com.solvd.university.service.JaxBService;
import com.solvd.university.service.StaxService;
import com.solvd.university.service.StudentContactService;
import com.solvd.university.service.impl.commonactions.StudentContactServiceCommonActions;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;
import com.solvd.university.util.parsers.JacksonServiceOperations;
import com.solvd.university.util.parsers.JaxbOperations;
import com.solvd.university.util.parsers.StaxServiceOperations;

public class StudentContactServiceJdbcImpl extends StudentContactServiceCommonActions implements StudentContactService {
    private final StaxService staxService = new StaxServiceOperations();
    private final JaxBService jaxBService = new JaxbOperations();
    private final JacksonService jacksonService = new JacksonServiceOperations();
    private final StudentContactRepository studentContactRepository = new StudentContactRepositoryJdbcImpl();

    @Override
    public void createStudentContact(Student student, XmlConsoleSelectors consoleSelector) {
        StudentContact studentContact = new StudentContact();
        switch (consoleSelector) {
            case CONSOLE -> studentContact = addContact();
            case STAX -> studentContact = staxService.readStudentContactFromXml();
            case JAXB -> studentContact = jaxBService.readStudentContactFromJaxb();
            case JACKSON -> studentContact = jacksonService.readStudentContactFromJackson();
        }

        studentContact.setStudentId(student.getStudentId());
        studentContactRepository.createStudentContact(studentContact);
    }
}
