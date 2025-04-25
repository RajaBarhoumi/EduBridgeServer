package dao;

import models.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void create(Course course) throws Exception {
        String sql = "INSERT INTO courses (title, description, level, professor_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getDescription());
            stmt.setString(3, course.getLevel().name());
            stmt.setInt(4, course.getProfessorId());
            stmt.executeUpdate();
        }
    }


    @Override
    public Course findById(int id) throws Exception {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        Course.Level.valueOf(rs.getString("level")),
                        rs.getInt("professor_id")
                );
            }
            return null;
        }
    }

    @Override
    public List<Course> getCoursesByProfessorId(int professorId) throws Exception {
        String sql = "SELECT * FROM courses WHERE professor_id = ?";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        Course.Level.valueOf(rs.getString("level")),
                        rs.getInt("professor_id")
                ));
            }
        }
        return courses;
    }


    @Override
    public List<Course> findAll() throws Exception {
        String sql = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        Course.Level.valueOf(rs.getString("level")),
                        rs.getInt("professor_id")
                ));
            }
        }
        return courses;
    }

    @Override
    public void update(Course course) throws Exception {
        String sql = "UPDATE courses SET title = ?, description = ?, level = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getDescription());
            stmt.setString(3, course.getLevel().name());
            stmt.setInt(4, course.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Course> getCoursesByStudent(int studentId) throws Exception {
        String sql = "SELECT c.* FROM courses c " +
                "JOIN enrollments e ON c.id = e.course_id " +
                "WHERE e.student_id = ?";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(mapRowToCourse(rs));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching courses by student: " + e.getMessage(), e);
        }

        return courses;
    }

    private Course mapRowToCourse(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                Course.Level.valueOf(rs.getString("level")),
                rs.getInt("professor_id")
        );
    }

    @Override
    public Course getCourseByTestId(int testId) throws Exception {
        String sql = "SELECT c.* FROM courses c " +
                "JOIN tests t ON c.id = t.course_id " +
                "WHERE t.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, testId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCourse(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching course by test ID: " + e.getMessage(), e);
        }

        return null;
    }

    @Override
    public int getCourseCountByProfessorId(int professorId) throws Exception {
        String sql = "SELECT COUNT(*) FROM courses WHERE professor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, professorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting courses by professor ID: " + e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public Map<String, Integer> getPassRateDistributionByCourse(int professorId) throws Exception {
        String sql = "SELECT c.title, COUNT(t.id) AS test_count " +
                "FROM courses c " +
                "LEFT JOIN tests t ON c.id = t.course_id " +
                "WHERE c.professor_id = ? " +
                "GROUP BY c.title";

        Map<String, Integer> distribution = new HashMap<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courseTitle = rs.getString("title");
                int testCount = rs.getInt("test_count");
                distribution.put(courseTitle, testCount);
            }

        } catch (SQLException e) {
            throw new Exception("Error getting pass rate distribution: " + e.getMessage(), e);
        }

        return distribution;
    }

}
