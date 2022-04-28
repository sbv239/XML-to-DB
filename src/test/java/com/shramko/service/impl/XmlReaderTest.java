package com.shramko.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XmlReaderTest {

    private final static String PATH = "src/test/resources/failed_persons.xml";
    private XmlReader reader;

    @Before
    public void setUp() {
        reader = XmlReader.getReader();
    }

    @Test
    public void testRead() {
        reader.read(PATH);
        Assert.assertEquals(4, reader.getData());
        Assert.assertEquals(0, reader.getCorrectData());
    }
}