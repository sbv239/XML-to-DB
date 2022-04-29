package com.shramko.component;

import com.shramko.component.impl.JdbcRepository;
import com.shramko.dto.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcRepositoryTest {

    private JdbcRepository repository;
    private Person person;

    @Before
    public void setUp() {
        repository = JdbcRepository.getRepository();
        person = new Person(590342911, "Boris", "Gershin", 43900);
        repository.insert(person);
    }
    @Test
    public void insertExistingPersonTest() {
        Assert.assertFalse(repository.insert(person));
    }
}