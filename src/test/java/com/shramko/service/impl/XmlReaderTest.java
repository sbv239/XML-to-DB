package com.shramko.service.impl;

import com.shramko.component.impl.JdbcRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XmlReaderTest {

    private final static String PATH = "src/test/resources/failed_persons.xml";
    private XmlReader reader;
    private JdbcRepository repository;

    @Before
    public void setUp() {
        repository = JdbcRepository.getRepository();
        reader = new XmlReader(repository);
    }

    @Test
    public void testRead() {

        reader.read(PATH);
        Assert.assertEquals(4, reader.getData());
        Assert.assertEquals(0, repository.getCorrectData());
    }
}