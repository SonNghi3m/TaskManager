package G45_Lexicon.dao.Impl;

import G45_Lexicon.DBConnectionException;
import G45_Lexicon.MySQLConnection;
import G45_Lexicon.dao.Impl.sequencer.PersonIdSequencer;
import G45_Lexicon.dao.PersonDAO;
import G45_Lexicon.model.Person;
import G45_Lexicon.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PersonDAOImpl implements PersonDAO {

    @Override
    public Person create(Person person) {
        if (person == null) throw new IllegalArgumentException("Person was null");
        String query = "insert into person(fullname, username, password, role) values (?, ?, ?, ?)";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, person.getFullName());
            preparedStatement.setString(2, person.getUserName());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.setString(4, person.getRole().toString());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) person.setPersonId(resultSet.getInt(1));
            }
            System.out.println("Person Id: " + person.getPersonId() + " was added!");
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(person);
        return person;
    }

    @Override
    public boolean deleteByID(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        String query = "delete from person where personId = ?";
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Person ID: " + id + " was deleted!");


        } catch(DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Person update(Person person) {

        String query = "update person set fullname = ?, username = ?, password = ?, role = ? where personId = ?";
    try (
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
        preparedStatement.setString(1, person.getFullName());
        preparedStatement.setString(2, person.getUserName());
        preparedStatement.setString(3, person.getPassword());
        preparedStatement.setString(4,person.getRole().toString());
        preparedStatement.setInt(5,person.getPersonId());

        preparedStatement.executeUpdate();
        System.out.println("Person Id: " + person.getPersonId() + " was updated!");

    } catch (DBConnectionException | SQLException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
        System.out.println(person);
    return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        String query = "select * from person";
        try (
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
                ) {
            while (resultSet.next()) {
                Person person = new Person();
                person.setPersonId(resultSet.getInt("personId"));
                person.setFullName(resultSet.getString("fullname"));
                person.setUserName(resultSet.getString("username"));
                person.setPassword(resultSet.getString("password"));
                person.setRole(Role.valueOf(resultSet.getString("role")));
                list.add(person);
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Person findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("id was null");
        String query = "select * from person where personId = ?";
        Person person = new Person();
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
                ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    person.setPersonId(resultSet.getInt("personId"));
                    person.setFullName(resultSet.getString("fullname"));
                    person.setUserName(resultSet.getString("username"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRole(Role.valueOf(resultSet.getString("role")));
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(person);
        return person;
    }

    @Override
    public List<Person> findByName(String name) {
        if (name == null) throw new IllegalArgumentException("name was null");
        List<Person> filteredList = new ArrayList<>();
        String query = "select * from person where fullname like ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setPersonId(resultSet.getInt("personId"));
                    person.setFullName(resultSet.getString("fullname"));
                    person.setUserName(resultSet.getString("username"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRole(Role.valueOf(resultSet.getString("role")));
                    filteredList.add(person);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }

    @Override
    public Optional<Person> findByUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("userName was null");
        String query = "select * from person where username = ?";
        Person person = new Person();
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    person.setPersonId(resultSet.getInt("personId"));
                    person.setFullName(resultSet.getString("fullname"));
                    person.setUserName(resultSet.getString("username"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRole(Role.valueOf(resultSet.getString("role")));
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(person);
        return Optional.of(person);
    }

    @Override
    public List<Person> findByRole(Role role) {
        if (role == null) throw new IllegalArgumentException("role was null");
        List<Person> filteredList = new ArrayList<>();
        String query = "select * from person where role = ?";
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, role.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setPersonId(resultSet.getInt("personId"));
                    person.setFullName(resultSet.getString("fullname"));
                    person.setUserName(resultSet.getString("username"));
                    person.setPassword(resultSet.getString("password"));
                    person.setRole(Role.valueOf(resultSet.getString("role")));
                    filteredList.add(person);
                }
            }
        }catch (DBConnectionException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(filteredList);
        return filteredList;
    }

}
