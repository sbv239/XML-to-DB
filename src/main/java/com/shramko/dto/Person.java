package com.shramko.dto;

import lombok.Data;

@Data
public class Person {
    private Integer uid;
    private String firstname;
    private String surname;
    private Integer salary;

    public Person(Integer uid, String firstname, String surname, Integer salary) {
        this.uid = uid;
        this.firstname = firstname;
        this.surname = surname;
        this.salary = salary;
    }

    public Person() {
    }
}
