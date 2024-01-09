package com.solvd.university.util.parsers;

import com.solvd.university.util.exceptions.NotValidXmlFileException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    public static void validateXml(File xmlFile, File xsdFile) throws NotValidXmlFileException {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (IOException | SAXException e) {
            throw new NotValidXmlFileException("Невалидный XML-файл, проверьте файл на соответствие XSD");
        }
    }
}