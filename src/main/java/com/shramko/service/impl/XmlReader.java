package com.shramko.service.impl;

import com.shramko.component.Repository;
import com.shramko.dto.Person;
import com.shramko.exception.XmlParserServiceException;
import com.shramko.service.Reader;
import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Slf4j
public class XmlReader implements Reader {

    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private final Repository repository;

    private int data;

    public XmlReader(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void read(String path) {
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            readByElement(xmlEventReader);
            logResult();
            xmlEventReader.close();
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new XmlParserServiceException("");
        }
    }

    private void readByElement(XMLEventReader reader) throws XMLStreamException {
        Person person = new Person();
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                String localPart = startElement.getName().getLocalPart();
                if (localPart.equals("person")) {
                    person = new Person();
                    data++;
                }
                switch (localPart) {
                    case "uid":
                        nextEvent = reader.nextEvent();
                        person.setUid(isDataEmpty(nextEvent) ? null : Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "first_name":
                        nextEvent = reader.nextEvent();
                        person.setFirstname(isDataEmpty(nextEvent) ? null : nextEvent.asCharacters().getData());
                        break;
                    case "last_name":
                        nextEvent = reader.nextEvent();
                        person.setSurname(isDataEmpty(nextEvent) ? null : nextEvent.asCharacters().getData());
                        break;
                    case "salary":
                        nextEvent = reader.nextEvent();
                        person.setSalary(isDataEmpty(nextEvent) ? null : Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    default:
                        break;
                }
            }
            savePerson(person, nextEvent);
        }
    }

    private boolean isDataEmpty(XMLEvent nextEvent) {
        return nextEvent.toString().startsWith("</");
    }

    private void savePerson(Person person, XMLEvent nextEvent) {
        if (nextEvent.isEndElement()) {
            EndElement endElement = nextEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals("person") && isValid(person)) {
                repository.insert(person);
            }
        }
    }

    private boolean isValid(Person person) {
        if (person.getFirstname() == null || person.getFirstname().equals("")) {
            return false;
        }
        if (person.getSurname() == null || person.getSurname().equals("")) {
            return false;
        }
        return person.getSalary() != null && person.getUid() != null;
    }

    private void logResult() {
        log.info("Data processed: " + data + "/" + repository.getCorrectData());
    }

    public int getData() {
        return data;
    }
}
