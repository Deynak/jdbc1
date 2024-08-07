package com.deynak.dao;

import com.deynak.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.deynak.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    @Override
    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(50) NOT NULL, "
                + "lastName VARCHAR(50) NOT NULL, "
                + "age INT, "
                + "PRIMARY KEY (id))";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(createTableSQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS users";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(dropTableSQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String deleteUserSQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM users";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {
            connection.setAutoCommit(false);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String cleanTableSQL = "TRUNCATE TABLE users";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(cleanTableSQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
