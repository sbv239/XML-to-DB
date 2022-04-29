package com.shramko.component.impl;

import com.shramko.component.DataSource;
import com.shramko.component.Repository;
import com.shramko.dto.Person;
import com.shramko.exception.XmlParserComponentException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JdbcRepository implements Repository {

    private static final JdbcRepository REPOSITORY = new JdbcRepository();

    private static final String CHECK_UID_SQL =
            "SELECT * FROM person WHERE uid=?";
    private static final String INSERT_PERSON_SQL =
            "INSERT INTO person (uid, name, surname, salary) VALUES (?, ?, ?, ?)";

    private int correctData;

    public static JdbcRepository getRepository() {
        return REPOSITORY;
    }

    private JdbcRepository() {
    }

    public boolean insert(Person person) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL)) {
            if (!isPersonExist(person, connection)) {
                statement.setInt(1, person.getUid());
                statement.setString(2, person.getFirstname());
                statement.setString(3, person.getSurname());
                statement.setInt(4, person.getSalary());
                statement.executeUpdate();
                correctData++;
                return true;
            }
        } catch (SQLException e) {
            throw new XmlParserComponentException(e.getMessage());
        }
        return false;
    }

    private boolean isPersonExist(Person person, Connection connection) throws SQLException {
        try (PreparedStatement selectStatement = connection.prepareStatement(CHECK_UID_SQL)) {
            selectStatement.setInt(1, person.getUid());
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                log.warn("Person with uid " + person.getUid() + " already exists");
                return true;
            }
        }
        return false;
    }

    public int getCorrectData() {
        return correctData;
    }
}
