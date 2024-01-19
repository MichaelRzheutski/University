package com.solvd.university.util.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.service.JacksonService;

import java.io.File;
import java.io.IOException;

public class JacksonServiceOperations implements JacksonService {
    @Override
    public Student readStudentFromJackson() {
        File jsonFile = new File("src/main/resources/json/student.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Student student;
        try {
            student = mapper.readValue(jsonFile, Student.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    @Override
    public StudentContact readStudentContactFromJackson() {
        File jsonFile = new File("src/main/resources/json/studentContact.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StudentContact studentContact;
        try {
            studentContact = mapper.readValue(jsonFile, StudentContact.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return studentContact;
    }
}
