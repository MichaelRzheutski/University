package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.persistence.LecturerContactRepository;
import com.solvd.university.service.LecturerContactService;
import com.solvd.university.service.impl.commonactions.LecturerContactServiceCA;
import com.solvd.university.service.impl.parsers.JacksonLecturer;
import com.solvd.university.service.impl.parsers.JaxbLecturer;
import com.solvd.university.service.impl.parsers.StaxLecturer;
import com.solvd.university.util.menus.enums.ParserSelectors;

public class LecturerContactServiceMybatisImpl extends LecturerContactServiceCA implements LecturerContactService {
    private final StaxLecturer staxLecturer;
    private final JaxbLecturer jaxbLecturer;
    private final JacksonLecturer jacksonLecturer;
    private final LecturerContactRepository lecturerContactRepository;

    public LecturerContactServiceMybatisImpl(
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
    public void createLecturerContact(Lecturer lecturer, ParserSelectors consoleSelector) {
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
