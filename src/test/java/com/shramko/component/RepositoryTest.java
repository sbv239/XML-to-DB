package com.shramko.component;

import com.shramko.dto.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryTest {

    private Repository repository;
    private Person person;

    @Before
    public void setUp() {
        repository = Repository.getRepository();
        person = new Person(590342911, "Boris", "Gershin", 43900);
    }
    @Test
    public void insertExistingPersonTest() {
        Assert.assertFalse(repository.insertPerson(person));
    }
}