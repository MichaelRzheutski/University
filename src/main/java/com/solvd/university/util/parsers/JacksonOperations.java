package com.solvd.university.util.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;

import java.io.File;
import java.io.IOException;

public class JacksonOperations {
    public Student readStudentFromJackson() {
        File jsonFile = new File("src/main/resources/json/student.json");
        ObjectMapper mapper = new ObjectMapper();
        Student student;
        try {
            student = mapper.readValue(jsonFile, Student.class);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    public StudentContact readStudentContactFromJackson() {
        File jsonFile = new File("src/main/resources/json/studentContact.json");
        ObjectMapper mapper = new ObjectMapper();
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
