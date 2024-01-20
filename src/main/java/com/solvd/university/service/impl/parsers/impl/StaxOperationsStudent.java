package com.solvd.university.service.impl.parsers.impl;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.StudentContact;
import com.solvd.university.service.impl.parsers.StaxStudent;
import com.solvd.university.util.exceptions.NotValidXmlFileException;
import com.solvd.university.util.parsers.XmlValidator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class StaxOperationsStudent implements StaxStudent {
    @Override
    public Student readStudentFromXml() {
        File xmlFile = new File("src/main/resources/xml/student.xml");
        File xsdFile = new File("src/main/resources/xml/student.xsd");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        Student student = new Student();

        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            XmlValidator.validateXml(xmlFile, xsdFile);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "firstName" -> student.setFirstName(reader.nextEvent().toString());
                        case "lastName" -> student.setLastName(reader.nextEvent().toString());
                        case "dateOfBirth" -> {
                            LocalDate date = LocalDate.parse(reader.nextEvent().toString());
                            student.setDateOfBirth(date);
                        }
                    }
                }
            }
        } catch (IOException | XMLStreamException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

    @Override
    public StudentContact readStudentContactFromXml() {
        File xmlFile = new File("src/main/resources/xml/studentContact.xml");
        File xsdFile = new File("src/main/resources/xml/studentContact.xsd");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        StudentContact studentContact = new StudentContact();

        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            XmlValidator.validateXml(xmlFile, xsdFile);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "phone" -> studentContact.setPhone(reader.nextEvent().toString());
                        case "email" -> studentContact.setEmail(reader.nextEvent().toString());
                    }
                }
            }
        } catch (IOException | XMLStreamException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return studentContact;
    }
}
