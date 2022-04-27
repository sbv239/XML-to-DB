package com.shramko;

import com.shramko.service.Reader;
import com.shramko.service.impl.XmlReader;

public class App {

    public static void main(String[] args) {
        String path = "src/main/resources/persons.xml";
        Reader reader = new XmlReader();
        reader.read(path);
    }
}
