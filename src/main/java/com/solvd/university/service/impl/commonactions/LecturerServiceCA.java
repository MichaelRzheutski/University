package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.service.impl.parsers.JacksonLecturer;
import com.solvd.university.service.impl.parsers.JaxbLecturer;
import com.solvd.university.service.impl.parsers.StaxLecturer;
import com.solvd.university.util.menus.enums.ParserSelectors;

import java.util.List;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class LecturerServiceCA {
    private final StaxLecturer staxLecturer;
    private final JaxbLecturer jaxbLecturer;
    private final JacksonLecturer jacksonLecturer;

    public LecturerServiceCA(
            StaxLecturer staxLecturer,
            JaxbLecturer jaxbLecturer,
            JacksonLecturer jacksonLecturer
    ) {
        this.staxLecturer = staxLecturer;
        this.jaxbLecturer = jaxbLecturer;
        this.jacksonLecturer = jacksonLecturer;
    }

    protected void printWholeLecturerInfo(List<Lecturer> lecturersWithContacts) {
        MY_LOGGER.info("\n" + ANSI_GREEN +
                "Id" + " | " +
                "Имя и Фамилия" + " | " +
                "Телефон" + " | " +
                "Email" + ANSI_RESET
        );
        for (Lecturer lecturer : lecturersWithContacts) {
            if (lecturer.getLecturerContact() != null) {
                MY_LOGGER.info("\n" + lecturer.getLecturerId() + " | " + ANSI_YELLOW +
                        lecturer.getFirstName() + " " +
                        lecturer.getLastName() + " | " +
                        lecturer.getLecturerContact().getPhone() + " | " +
                        lecturer.getLecturerContact().getEmail() + ANSI_RESET
                );
            } else {
                MY_LOGGER.info("\n" + lecturer.getLecturerId() + " | " + ANSI_YELLOW +
                        lecturer.getFirstName() + " " +
                        lecturer.getLastName() + " | " +
                        "Телефон не указан" + " | " +
                        "Email не указан" + ANSI_RESET
                );
            }
        }

        System.out.println();
    }

    protected List<Lecturer> setLecturerContactData(List<Lecturer> lecturerList, List<LecturerContact> contactList) {
        for (Lecturer lecturer : lecturerList) {
            for (LecturerContact lecturerContact : contactList) {
                if (lecturerContact.getLecturerId().equals(lecturer.getLecturerId())) {
                    lecturer.setLecturerContact(lecturerContact);
                }
            }
        }

        return lecturerList;
    }

    protected Lecturer addLecturer(ParserSelectors xmlConsoleSelector) {
        Lecturer lecturerToCreate = new Lecturer();
        switch (xmlConsoleSelector) {
            case CONSOLE -> lecturerToCreate = collectLecturerData();
            case STAX -> lecturerToCreate = staxLecturer.readLecturerFromXml();
            case JAXB -> lecturerToCreate = jaxbLecturer.readLecturerFromJaxb();
            case JACKSON -> lecturerToCreate = jacksonLecturer.readLecturerFromJackson();
        }
        return lecturerToCreate;
    }

    protected Lecturer getLecturerById() {
        Scanner scanner = new Scanner(System.in);
        Lecturer lecturer = new Lecturer();

        MY_LOGGER.info(ANSI_GREEN + "Введите ID преподавателя:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            lecturer.setLecturerId(scanner.nextLong());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }
        return lecturer;
    }

    protected Lecturer editInfo() {
        Scanner scanner = new Scanner(System.in);
        Lecturer lecturer;
        Long enteredLecturertId = null;

        MY_LOGGER.info(ANSI_GREEN + "Введите ID преподавателя:" + ANSI_RESET);
        if (scanner.hasNextLong()) {
            enteredLecturertId = scanner.nextLong();
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        lecturer = collectLecturerData();
        lecturer.setLecturerId(enteredLecturertId);

        return lecturer;
    }

    private Lecturer collectLecturerData() {
        Scanner scanner = new Scanner(System.in);
        Lecturer lecturer = new Lecturer();

        MY_LOGGER.info(ANSI_GREEN + "Введите имя преподавателя:" + ANSI_RESET);
        if (scanner.hasNext()) {
            lecturer.setFirstName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        MY_LOGGER.info(ANSI_GREEN + "Введите фамилию преподавателя:" + ANSI_RESET);
        if (scanner.hasNext()) {
            lecturer.setLastName(scanner.nextLine());
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return lecturer;
    }
}
