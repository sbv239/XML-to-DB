package com.shramko.domain;

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

    public boolean isValid() {
        if (uid == null) {
            return false;
        }
        if (firstname == null || firstname.equals("")) {
            return false;
        }
        if (surname == null || surname.equals("")) {
            return false;
        }
        if (salary == null) {
            return false;
        }
        return true;
    }
}
