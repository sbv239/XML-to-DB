package com.shramko.service.impl;

import com.shramko.component.Repository;
import com.shramko.domain.Person;
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

    private static final Repository repository = Repository.getRepository();
    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    private int data;
    private int correctData;

    @Override
    public void read(String path) {
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            Person person = new Person();
            readByElement(reader, person);
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        correctData = repository.getCorrectData();
        log.info("Data processed: " + data + "/" + correctData);
    }

    private void readByElement(XMLEventReader reader, Person person) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                String localPart = startElement.getName().getLocalPart();
                switch (localPart) {
                    case "person":
                        person = new Person();
                        data++;
                        break;
                    case "uid":
                        nextEvent = reader.nextEvent();
                        if (isDataEmpty(nextEvent)) break;
                        person.setUid(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "first_name":
                        nextEvent = reader.nextEvent();
                        if (isDataEmpty(nextEvent)) break;
                        person.setFirstname(nextEvent.asCharacters().getData());
                        break;
                    case "last_name":
                        nextEvent = reader.nextEvent();
                        if (isDataEmpty(nextEvent)) break;
                        person.setSurname(nextEvent.asCharacters().getData());
                        break;
                    case "salary":
                        nextEvent = reader.nextEvent();
                        if (isDataEmpty(nextEvent)) break;
                        person.setSalary(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    default:
                        break;
                }
            }
            savePerson(person, nextEvent);
        }
    }

    private boolean isDataEmpty(XMLEvent nextEvent) {
        if (nextEvent.toString().startsWith("</")) {
            return true;
        }
        return false;
    }

    private void savePerson(Person person, XMLEvent nextEvent) {
        if (nextEvent.isEndElement()) {
            EndElement endElement = nextEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals("person") && person.isValid()) {
                repository.insertPerson(person);
            }
        }
    }

    public int getData() {
        return data;
    }

    public int getCorrectData() {
        return correctData;
    }
}
