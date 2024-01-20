package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.persistence.LecturerContactRepository;
import com.solvd.university.persistence.LecturerRepository;
import com.solvd.university.service.LecturerContactService;
import com.solvd.university.service.LecturerDepartmentService;
import com.solvd.university.service.LecturerService;
import com.solvd.university.service.impl.commonactions.LecturerServiceCommonActions;
import com.solvd.university.service.impl.parsers.JacksonLecturer;
import com.solvd.university.service.impl.parsers.JaxbLecturer;
import com.solvd.university.service.impl.parsers.StaxLecturer;
import com.solvd.university.util.menus.enums.XmlConsoleSelectors;

import java.util.List;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class LecturerServiceMybatisImpl extends LecturerServiceCommonActions implements LecturerService {
    private final LecturerDepartmentService lecturerDepartmentService;
    private final LecturerContactRepository lecturerContactRepository;
    private final LecturerRepository lecturerRepository;
    private final LecturerContactService lecturerContactService;

    public LecturerServiceMybatisImpl(
            StaxLecturer staxLecturer,
            JaxbLecturer jaxBLecturer,
            JacksonLecturer jacksonLecturer,
            LecturerDepartmentService lecturerDepartmentService,
            LecturerContactRepository lecturerContactRepository,
            LecturerRepository lecturerRepository,
            LecturerContactService lecturerContactService
    ) {
        super(staxLecturer, jaxBLecturer, jacksonLecturer);
        this.lecturerDepartmentService = lecturerDepartmentService;
        this.lecturerContactRepository = lecturerContactRepository;
        this.lecturerRepository = lecturerRepository;
        this.lecturerContactService = lecturerContactService;
    }

    private List<Lecturer> getLecturersWithContacts() {
        List<Lecturer> lecturerList = lecturerDepartmentService.getLecturersWithDepartments();
        List<LecturerContact> contactList = lecturerContactRepository.getAllLecturerContacts();

        return setLecturerContactData(lecturerList, contactList);
    }

    @Override
    public void printFullLecturerInfo() {
        List<Lecturer> lecturersWithContacts = getLecturersWithContacts();
        printWholeLecturerInfo(lecturersWithContacts);
    }

    @Override
    public void employLecturer(XmlConsoleSelectors xmlConsoleSelector) {
        Lecturer lecturerToEmploy = addLecturer(xmlConsoleSelector);
        lecturerRepository.create(lecturerToEmploy);
        lecturerContactService.createLecturerContact(lecturerToEmploy, xmlConsoleSelector);
        MY_LOGGER.info(ANSI_GREEN + "\n" + "Преподаватель был добавлен в базу:" + "\n" +
                "Id" + " | " +
                "Имя и фамилия" + " | " +
                "Дата рождения" + " | " + ANSI_YELLOW + "\n" +
                lecturerToEmploy.getLecturerId() + " | " +
                lecturerToEmploy.getFirstName() + " " +
                lecturerToEmploy.getLastName() + ANSI_RESET + "\n"
        );
    }

    @Override
    public Lecturer findLecturer() {
        Lecturer lecturer = getLecturerById();
        Lecturer foundLecturer = lecturerRepository.findById(lecturer);
        MY_LOGGER.info(ANSI_GREEN + "Найден преподаватель: " + ANSI_YELLOW +
                foundLecturer.getLecturerId() + " | " +
                foundLecturer.getFirstName() + " " +
                foundLecturer.getLastName() + ANSI_RESET + "\n");

        return foundLecturer;
    }

    @Override
    public void editLecturerInfo() {
        Lecturer lecturerToUpdateInfo = editInfo();
        lecturerRepository.update(lecturerToUpdateInfo);
        MY_LOGGER.info(ANSI_GREEN + "Обновлена информация у преподавателя с ID: " + ANSI_YELLOW
                + lecturerToUpdateInfo.getLecturerId() + ANSI_RESET + "\n");
    }

    @Override
    public void dismissLecturerById() {
        Lecturer lecturer = getLecturerById();
        Lecturer lecturerToDelete = lecturerRepository.findById(lecturer);
        lecturerRepository.deleteById(lecturerToDelete);
        MY_LOGGER.info(ANSI_GREEN + "Удалён преподаватель: " + ANSI_YELLOW +
                lecturerToDelete.getLecturerId() + " | " +
                lecturerToDelete.getFirstName() + " " +
                lecturerToDelete.getLastName() + ANSI_RESET + "\n"
        );
    }

    @Override
    public void printNumberOfEntries() {
        MY_LOGGER.info(ANSI_GREEN + "Общее количество преподавателей: " + ANSI_YELLOW
                + lecturerRepository.countOfEntries() + ANSI_RESET + "\n");
    }
}
