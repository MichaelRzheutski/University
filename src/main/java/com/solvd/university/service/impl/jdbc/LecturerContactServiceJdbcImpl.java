package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.persistence.LecturerContactRepository;
import com.solvd.university.service.LecturerContactService;
import com.solvd.university.service.impl.commonactions.LecturerContactServiceCommonActions;
import com.solvd.university.service.impl.parsers.JacksonLecturer;
import com.solvd.university.service.impl.parsers.JaxbLecturer;
import com.solvd.university.service.impl.parsers.StaxLecturer;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

public class LecturerContactServiceJdbcImpl extends LecturerContactServiceCommonActions implements LecturerContactService {
    private final StaxLecturer staxLecturer;
    private final JaxbLecturer jaxbLecturer;
    private final JacksonLecturer jacksonLecturer;
    private final LecturerContactRepository lecturerContactRepository;

    public LecturerContactServiceJdbcImpl(
            StaxLecturer staxLecturer,
            JaxbLecturer jaxbLecturer,
            JacksonLecturer jacksonLecturer,
            LecturerContactRepository lecturerContactRepository
    ) {
        this.staxLecturer = staxLecturer;
        this.jaxbLecturer = jaxbLecturer;
        this.jacksonLecturer = jacksonLecturer;
        this.lecturerContactRepository = lecturerContactRepository;
    }

    @Override
    public void createLecturerContact(Lecturer lecturer, XmlConsoleSelectors consoleSelector) {
        LecturerContact lecturerContact = new LecturerContact();
        switch (consoleSelector) {
            case CONSOLE -> lecturerContact = addContact();
            case STAX -> lecturerContact = staxLecturer.readLecturerContactFromXml();
            case JAXB -> lecturerContact = jaxbLecturer.readLecturerContactFromJaxb();
            case JACKSON -> lecturerContact = jacksonLecturer.readLecturerContactFromJackson();
        }

        lecturerContact.setLecturerId(lecturer.getLecturerId());
        lecturerContactRepository.createLecturerContact(lecturerContact);
    }
}
