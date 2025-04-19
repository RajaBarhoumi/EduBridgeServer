package dao;

import models.Certificate;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificateDAOImpl implements CertificateDAO {
    @Override
    public void issueCertificate(int studentId, int testId, double score) throws Exception {
        String sql = "INSERT INTO certificates (student_id, test_id, issue_date, is_valid, score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, testId);
            stmt.setDate(3, new java.sql.Date(new Date().getTime()));
            stmt.setBoolean(4, true);
            stmt.setDouble(5, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error issuing certificate: " + e.getMessage(), e);
        }
    }

    @Override
    public Certificate findById(int id) throws Exception {
        String sql = "SELECT * FROM certificates WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Certificate(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("test_id"),
                            rs.getDate("issue_date"),
                            rs.getBoolean("is_valid"),
                            rs.getDouble("score")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error finding certificate by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Certificate> findByStudentId(int studentId) throws Exception {
        String sql = "SELECT * FROM certificates WHERE student_id = ?";
        List<Certificate> certificates = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    certificates.add(new Certificate(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("test_id"),
                            rs.getDate("issue_date"),
                            rs.getBoolean("is_valid"),
                            rs.getDouble("score")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error finding certificates by student ID: " + e.getMessage(), e);
        }
        return certificates;
    }

    @Override
    public void invalidateCertificate(int certificateId) throws Exception {
        String sql = "UPDATE certificates SET is_valid = false WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, certificateId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error invalidating certificate: " + e.getMessage(), e);
        }
    }
}
