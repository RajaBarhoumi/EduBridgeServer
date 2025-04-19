package dao;

import models.Question;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    @Override
    public void addQuestion(Question question) throws Exception {
        String sql = "INSERT INTO questions (content, points, test_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, question.getContent());
            stmt.setInt(2, question.getPoints());
            stmt.setInt(3, question.getTestId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error adding question: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Question> getQuestionsByTest(int testId) throws Exception {
        String sql = "SELECT * FROM questions WHERE test_id = ?";
        List<Question> questions = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, testId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(new Question(
                            rs.getInt("id"),
                            rs.getString("content"),
                            rs.getInt("points"),
                            rs.getInt("test_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching questions: " + e.getMessage(), e);
        }

        return questions;
    }

    @Override
    public void deleteQuestion(int id) throws Exception {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error deleting question: " + e.getMessage(), e);
        }
    }
}
