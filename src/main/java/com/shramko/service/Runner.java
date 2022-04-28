package com.shramko.service;

import com.shramko.dto.Extension;
import com.shramko.service.impl.XmlReader;

import java.io.IOException;

public class Runner {

    private Reader reader = XmlReader.getReader();

    public void run(String... files) throws IOException {
        if (files == null || files.length == 0) {
            throw new IOException("No input file");
        }
        if (Extension.getFileExtension(files[0]).equals(Extension.XML)) {
            reader.read(files[0]);
        } else {
            throw new IOException("Unknown file format");
        }
    }
}
