package dao;

import models.StudentTest;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTestDAOImpl implements StudentTestDAO {

    @Override
    public void addStudentTest(StudentTest studentTest) throws Exception {
        String sql = "INSERT INTO student_tests (student_id, test_id, score, passed, certificate_generated, taken_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentTest.getStudentId());
            stmt.setInt(2, studentTest.getTestId());
            stmt.setFloat(3, studentTest.getScore());
            stmt.setBoolean(4, studentTest.isPassed());
            stmt.setBoolean(5, studentTest.isCertificateGenerated());
            stmt.setTimestamp(6, studentTest.getTakenDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error adding student test: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentTest findById(int id) throws Exception {
        String sql = "SELECT * FROM student_tests WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StudentTest(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("test_id"),
                            rs.getFloat("score"),
                            rs.getBoolean("passed"),
                            rs.getBoolean("certificate_generated"),
                            rs.getTimestamp("taken_date")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error finding student test by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentTest> findByStudentId(int studentId) throws Exception {
        String sql = "SELECT * FROM student_tests WHERE student_id = ?";
        List<StudentTest> studentTests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    studentTests.add(new StudentTest(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("test_id"),
                            rs.getFloat("score"),
                            rs.getBoolean("passed"),
                            rs.getBoolean("certificate_generated"),
                            rs.getTimestamp("taken_date")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error finding student tests by student ID: " + e.getMessage(), e);
        }
        return studentTests;
    }

    @Override
    public List<StudentTest> findByTestId(int testId) throws Exception {
        String sql = "SELECT * FROM student_tests WHERE test_id = ?";
        List<StudentTest> studentTests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, testId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    studentTests.add(new StudentTest(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("test_id"),
                            rs.getFloat("score"),
                            rs.getBoolean("passed"),
                            rs.getBoolean("certificate_generated"),
                            rs.getTimestamp("taken_date")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error finding student tests by test ID: " + e.getMessage(), e);
        }
        return studentTests;
    }

    @Override
    public void updateStudentTest(StudentTest studentTest) throws Exception {
        String sql = "UPDATE student_tests SET score = ?, passed = ?, certificate_generated = ?, taken_date = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, studentTest.getScore());
            stmt.setBoolean(2, studentTest.isPassed());
            stmt.setBoolean(3, studentTest.isCertificateGenerated());
            stmt.setTimestamp(4, studentTest.getTakenDate());
            stmt.setInt(5, studentTest.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error updating student test: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudentTest(int studentTestId) throws Exception {
        String sql = "DELETE FROM student_tests WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentTestId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error deleting student test: " + e.getMessage(), e);
        }
    }
}
