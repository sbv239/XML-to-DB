package com.shramko.service;

import com.shramko.dto.Extension;
import com.shramko.exception.XmlParserServiceException;

public class Runner {

    private final Reader reader;

    public Runner(Reader reader) {
        this.reader = reader;
    }

    public void run(String... files) {

        if (files == null || files.length == 0) {
            throw new XmlParserServiceException("No input file");
        }
        if (files.length > 1) {
            throw new XmlParserServiceException("Only one file allowed");
        }
        if (!Extension.getFileExtension(files[0]).equals(Extension.XML)) {
            throw new XmlParserServiceException("Unknown file format");
        }

        // Use "src/main/resources/persons.xml" instead of files[0] to test prepared file from IDE
        reader.read(files[0]);
    }
}
