package dao;

import models.Option;
import models.Question;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDAOImpl implements OptionDAO {

    @Override
    public void addOption(Option option) throws Exception {
        if (option.isCorrect()) {
            String checkSql = "SELECT COUNT(*) FROM options WHERE question_id = ? AND is_correct = true";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

                checkStmt.setInt(1, option.getQuestionId());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new Exception("Only one correct option is allowed per question.");
                    }
                }
            } catch (SQLException e) {
                throw new Exception("Error checking existing correct option: " + e.getMessage(), e);
            }
        }

        String sql = "INSERT INTO options (content, is_correct, question_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, option.getContent());
            stmt.setBoolean(2, option.isCorrect());
            stmt.setInt(3, option.getQuestionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error adding option: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Option> getOptionsByQuestion(int questionId) throws Exception {
        String sql = "SELECT * FROM options WHERE question_id = ?";
        List<Option> options = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    options.add(new Option(
                            rs.getInt("id"),
                            rs.getString("content"),
                            rs.getBoolean("is_correct"),
                            rs.getInt("question_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching options: " + e.getMessage(), e);
        }

        return options;
    }

    @Override
    public void deleteOption(int id) throws Exception {
        String sql = "DELETE FROM options WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error deleting option: " + e.getMessage(), e);
        }
    }


    @Override
    public void updateOption(Option option) throws Exception {
        String sql = "UPDATE options SET is_correct = ?, content = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, option.isCorrect());
            stmt.setString(2, option.getContent());
            stmt.setInt(3, option.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error updating test: " + e.getMessage(), e);
        }
    }
}
