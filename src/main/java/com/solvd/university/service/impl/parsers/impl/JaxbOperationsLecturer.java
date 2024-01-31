package com.solvd.university.service.impl.parsers.impl;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.service.impl.parsers.JaxbLecturer;
import com.solvd.university.util.exceptions.NotValidXmlFileException;
import com.solvd.university.util.parsers.XmlValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbOperationsLecturer implements JaxbLecturer {
    @Override
    public Lecturer readLecturerFromJaxb() {
        File xmlFile = new File("src/main/resources/xml/lecturer.xml");
        File xsdFile = new File("src/main/resources/xml/lecturer.xsd");
        Lecturer lecturer;

        try {
            XmlValidator.validateXml(xmlFile, xsdFile);
            JAXBContext context = JAXBContext.newInstance(Lecturer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            lecturer = (Lecturer) unmarshaller.unmarshal(xmlFile);

        } catch (JAXBException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return lecturer;
    }

    @Override
    public LecturerContact readLecturerContactFromJaxb() {
        File xmlFile = new File("src/main/resources/xml/lecturerContact.xml");
        File xsdFile = new File("src/main/resources/xml/lecturerContact.xsd");
        LecturerContact lecturerContact;

        try {
            XmlValidator.validateXml(xmlFile, xsdFile);
            JAXBContext context = JAXBContext.newInstance(Lecturer.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            lecturerContact = (LecturerContact) unmarshaller.unmarshal(xmlFile);

        } catch (JAXBException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return lecturerContact;
    }
}
