package com.shramko.component;

import com.shramko.dto.Person;

public interface Repository {
    boolean insert(Person person);

    int getCorrectData();
}
