package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        final String CREAT_NEW_TABLE = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NULL,\n" +
                "  lastName VARCHAR(45) NULL,\n" +
                "  age INT(2) NULL,\n" +
                "  PRIMARY KEY (id));";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREAT_NEW_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        final String DROP_TABLE = "DROP TABLE IF EXISTS users";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        final String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        final String REMOVE_USER = "DELETE FROM users WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        final String SELECT_ALL = "SELECT id, name, lastName, age FROM users";
        List<User> listOfUsers = new ArrayList<>();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {

        String CLEAN_TABLE = "TRUNCATE users";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
