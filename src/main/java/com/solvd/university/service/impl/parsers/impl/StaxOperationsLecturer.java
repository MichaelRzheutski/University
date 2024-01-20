package com.solvd.university.service.impl.parsers.impl;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.domain.LecturerContact;
import com.solvd.university.service.impl.parsers.StaxLecturer;
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

public class StaxOperationsLecturer implements StaxLecturer {
    @Override
    public Lecturer readLecturerFromXml() {
        File xmlFile = new File("src/main/resources/xml/lecturer.xml");
        File xsdFile = new File("src/main/resources/xml/lecturer.xsd");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        Lecturer lecturer = new Lecturer();

        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            XmlValidator.validateXml(xmlFile, xsdFile);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "firstName" -> lecturer.setFirstName(reader.nextEvent().toString());
                        case "lastName" -> lecturer.setLastName(reader.nextEvent().toString());
                    }
                }
            }
        } catch (IOException | XMLStreamException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return lecturer;
    }

    @Override
    public LecturerContact readLecturerContactFromXml() {
        File xmlFile = new File("src/main/resources/xml/lecturerContact.xml");
        File xsdFile = new File("src/main/resources/xml/lecturerContact.xsd");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        LecturerContact lecturerContact = new LecturerContact();

        try (FileInputStream fis = new FileInputStream(xmlFile)) {
            XmlValidator.validateXml(xmlFile, xsdFile);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fis);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "phone" -> lecturerContact.setPhone(reader.nextEvent().toString());
                        case "email" -> lecturerContact.setEmail(reader.nextEvent().toString());
                    }
                }
            }
        } catch (IOException | XMLStreamException | NotValidXmlFileException e) {
            throw new RuntimeException(e);
        }

        return lecturerContact;
    }
}
