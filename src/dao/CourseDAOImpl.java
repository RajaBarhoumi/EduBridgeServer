package dao;

import models.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
