package com.solvd.university.service.impl.parsers.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.service.impl.parsers.JacksonLecturer;

import java.io.File;
import java.io.IOException;

public class JacksonOperationsLecturer implements JacksonLecturer {
    @Override
    public Lecturer readLecturerFromJackson() {
        File jsonFile = new File("src/main/resources/json/student.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Lecturer lecturer;
        try {
            lecturer = mapper.readValue(jsonFile, Lecturer.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return lecturer;
    }

    @Override
    public LecturerContact readLecturerContactFromJackson() {
        File jsonFile = new File("src/main/resources/json/studentContact.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LecturerContact lecturerContact;
        try {
            lecturerContact = mapper.readValue(jsonFile, LecturerContact.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return lecturerContact;
    }
}
