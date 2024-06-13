package repository;

import entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    private final String jdbcURL = "jdbc:postgresql://localhost:5433/postgres?currentSchema=online_store";
    private final String jdbcUsername = "postgres";
    private final String jdbcPassword = "123456";

    public User findByUsername(String username) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                user = new User(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    } public void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
