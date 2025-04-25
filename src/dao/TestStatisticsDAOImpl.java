package dao;

import models.TestStatistics;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestStatisticsDAOImpl implements TestStatisticsDAO {

    private final Connection conn;

    public TestStatisticsDAOImpl() throws Exception {
        conn = DBConnection.getConnection();
    }

    @Override
    public void insert(TestStatistics stats) throws Exception {
        String sql = "INSERT INTO test_statistics (test_id, total_attempts, average_score, passing_rate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stats.getTestId());
            stmt.setInt(2, stats.getTotalAttempts());
            stmt.setFloat(3, stats.getAverageScore());
            stmt.setFloat(4, stats.getPassingRate());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(TestStatistics stats) throws Exception {
        String sql = "UPDATE test_statistics SET total_attempts = ?, average_score = ?, passing_rate = ? WHERE test_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stats.getTotalAttempts());
            stmt.setFloat(2, stats.getAverageScore());
            stmt.setFloat(3, stats.getPassingRate());
            stmt.setInt(4, stats.getTestId());
            stmt.executeUpdate();
        }
    }

    @Override
    public TestStatistics findByTestId(int testId) throws Exception {
        String sql = "SELECT * FROM test_statistics WHERE test_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, testId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TestStatistics(
                        rs.getInt("id"),
                        rs.getInt("test_id"),
                        rs.getInt("total_attempts"),
                        rs.getFloat("average_score"),
                        rs.getFloat("passing_rate")
                );
            }
            return null;
        }
    }
}
