package com.shramko.component;

import com.shramko.domain.Person;

import java.sql.*;

public class Repository {

    private static Repository repository = new Repository();

    private static final String CHECK_UID_SQL = "SELECT * FROM person WHERE uid=?";
    private static final String INSERT_PERSON_SQL = "INSERT INTO person (uid, name, surname, salary) VALUES (?, ?, ?, ?)";

    private int correctData;

    public static Repository getRepository() {
        return repository;
    }

    private Repository() {
    }

    public boolean insertPerson(Person person) {
        try (Connection connection = DataSource.getConnection()) {
            if (!isPersonExist(person, connection)) {
                PreparedStatement statement = connection.prepareStatement(INSERT_PERSON_SQL);
                statement.setInt(1, person.getUid());
                statement.setString(2, person.getFirstname());
                statement.setString(3, person.getSurname());
                statement.setInt(4, person.getSalary());
                statement.executeUpdate();
                correctData++;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPersonExist(Person person, Connection connection) throws SQLException {
        try (PreparedStatement selectStatement = connection.prepareStatement(CHECK_UID_SQL)) {
            selectStatement.setInt(1, person.getUid());
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Person with uid " + person.getUid() + " already exists");
                return true;
            }
        }
        return false;
    }

    public int getCorrectData() {
        return correctData;
    }
}
