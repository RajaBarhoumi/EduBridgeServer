package dao;

import models.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void register(String name, String email, String password, String role) throws Exception {
        String checkSql = "SELECT COUNT(*) FROM users WHERE email = ?";
        String insertSql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Check if email already exists
            checkStmt.setString(1, email);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new Exception("A user with this email already exists.");
                }
            }

            // If email doesn't exist, insert the new user
            insertStmt.setString(1, name);
            insertStmt.setString(2, email);
            insertStmt.setString(3, password);
            insertStmt.setString(4, role);
            insertStmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error registering user: " + e.getMessage(), e);
        }
    }


    @Override
    public User login(String email, String password) throws Exception {
        User user = findByEmail(email);
        if (user == null || !password.equals(user.getPassword())) {
            throw new Exception("Invalid email or password");
        }
        return user;
    }

    @Override
    public User findById(int id) throws Exception {
        String sql = "SELECT id, name, email, password, role FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            User.Role.valueOf(rs.getString("role"))
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error finding user by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public User findByEmail(String email) throws Exception {
        String sql = "SELECT id, name, email, password, role FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            User.Role.valueOf(rs.getString("role"))
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error finding user by email: " + e.getMessage(), e);
        }
    }

    @Override
    public User getStudentByStudentTestId(int studentTestId) throws Exception {
        String sql = "SELECT u.id, u.name, u.email, u.password, u.role " +
                "FROM users u " +
                "JOIN student_tests st ON u.id = st.student_id " +
                "WHERE st.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentTestId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            User.Role.valueOf(rs.getString("role"))
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching student by StudentTest ID: " + e.getMessage(), e);
        }
    }

}