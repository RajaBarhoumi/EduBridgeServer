package dao;

import models.Enrollment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public void enrollStudent(Enrollment enrollment) throws Exception {
        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setDate(3, new java.sql.Date(enrollment.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error enrolling student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws Exception {
        String sql = "SELECT * FROM enrollments WHERE student_id = ?";
        List<Enrollment> enrollments = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    enrollments.add(new Enrollment(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id"),
                            rs.getDate("enrollment_date")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error getting enrollments: " + e.getMessage(), e);
        }

        return enrollments;
    }

    @Override
    public void deleteEnrollment(int id) throws Exception {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error deleting enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCourseCountByStudentId(int studentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting courses for student ID: " + e.getMessage(), e);
        }
        return 0;
    }

}
