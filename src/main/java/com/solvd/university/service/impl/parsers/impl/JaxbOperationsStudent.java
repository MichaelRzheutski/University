package com.solvd.university.service.impl.parsers.impl;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.service.impl.parsers.JaxbStudent;
import com.solvd.university.util.exceptions.NotValidXmlFileException;
import com.solvd.university.util.parsers.XmlValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbOperationsStudent implements JaxbStudent {
    @Override
    public Student readStudentFromJaxb() {
        File xmlFile = new File("src/main/resources/xml/student.xml");
        File xsdFile = new File("src/main/resources/xml/student.xsd");
        Student student;

        try {
            XmlValidator.validateXml(xmlFile, xsdFile);
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            student = (Student) unmarshaller.unmarshal(xmlFile);

        } catch (JAXBException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    @Override
    public StudentContact readStudentContactFromJaxb() {
        File xmlFile = new File("src/main/resources/xml/studentContact.xml");
        File xsdFile = new File("src/main/resources/xml/studentContact.xsd");
        StudentContact studentContact;

        try {
            XmlValidator.validateXml(xmlFile, xsdFile);
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            studentContact = (StudentContact) unmarshaller.unmarshal(xmlFile);

        } catch (JAXBException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return studentContact;
    }
}
