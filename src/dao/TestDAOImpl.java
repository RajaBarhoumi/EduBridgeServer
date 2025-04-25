package dao;

import models.Test;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {

    @Override
    public void addTest(Test test) throws Exception {
        String sql = "INSERT INTO tests (title, course_id, professor_id, time_limit) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, test.getTitle());
            stmt.setInt(2, test.getCourseId());
            stmt.setInt(3, test.getProfessorId());
            stmt.setInt(4, test.getTimeLimit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error adding test: " + e.getMessage(), e);
        }
    }

    @Override
    public Test findById(int id) throws Exception {
        String sql = "SELECT * FROM tests WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Test(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("course_id"),
                            rs.getInt("professor_id"),
                            rs.getInt("time_limit")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error finding test by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Test> findByCourseId(int courseId) throws Exception {
        String sql = "SELECT * FROM tests WHERE course_id = ?";
        List<Test> tests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tests.add(new Test(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("course_id"),
                            rs.getInt("professor_id"),
                            rs.getInt("time_limit")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error finding tests by course ID: " + e.getMessage(), e);
        }
        return tests;
    }

    @Override
    public List<Test> findByProfessorId(int professorId) throws Exception {
        String sql = "SELECT * FROM tests WHERE professor_id = ?";
        List<Test> tests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tests.add(new Test(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("course_id"),
                            rs.getInt("professor_id"),
                            rs.getInt("time_limit")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error finding tests by professor ID: " + e.getMessage(), e);
        }
        return tests;
    }

    @Override
    public void updateTest(Test test) throws Exception {
        String sql = "UPDATE tests SET title = ?, course_id = ?, professor_id = ?, time_limit = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, test.getTitle());
            stmt.setInt(2, test.getCourseId());
            stmt.setInt(3, test.getProfessorId());
            stmt.setInt(4, test.getTimeLimit());
            stmt.setInt(5, test.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error updating test: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTest(int testId) throws Exception {
        String sql = "DELETE FROM tests WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, testId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error deleting test: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Test> findByStudentId(int studentId) throws Exception {
        List<Test> tests = new ArrayList<>();

        String query = "SELECT t.id, t.title, t.course_id, t.professor_id, t.time_limit " +
                "FROM tests t " +
                "INNER JOIN enrollments e ON t.course_id = e.course_id " +
                "WHERE e.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setTitle(rs.getString("title"));
                test.setCourseId(rs.getInt("course_id"));
                test.setProfessorId(rs.getInt("professor_id"));
                test.setTimeLimit(rs.getInt("time_limit"));
                tests.add(test);
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            throw new Exception("Error fetching tests for student", e);
        }

        return tests;
    }

    @Override
    public int getNumberOfQuestions(int testId) throws Exception {
        String sql = "SELECT COUNT(*) FROM questions WHERE test_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, testId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting questions for test: " + e.getMessage(), e);
        }
    }

    @Override
    public int getTestCountByProfessorId(int professorId) throws Exception {
        String sql = "SELECT COUNT(*) FROM tests WHERE professor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting tests for professor ID: " + e.getMessage(), e);
        }
        return 0;
    }


}
