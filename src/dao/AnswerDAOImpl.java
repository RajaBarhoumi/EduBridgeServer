package dao;

import models.Answer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {

    @Override
    public void saveAnswer(Answer answer) throws Exception {
        String sql = "INSERT INTO answers (student_test_id, question_id, selected_option_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, answer.getStudentTestId());
            stmt.setInt(2, answer.getQuestionId());
            stmt.setInt(3, answer.getSelectedOptionId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error saving answer: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws Exception {
        String sql = """
            SELECT a.id, a.student_id, a.question_id, a.selected_option_id
            FROM answers a
            JOIN questions q ON a.question_id = q.id
            WHERE a.student_id = ? AND q.test_id = ?
        """;

        List<Answer> answers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, testId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Answer a = new Answer(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getInt("question_id"),
                            rs.getInt("selected_option_id")
                    );
                    answers.add(a);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error retrieving answers: " + e.getMessage(), e);
        }

        return answers;
    }

    @Override
    public boolean isAnswerCorrect(int selectedOptionId) throws Exception {
        String sql = "SELECT is_correct FROM options WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, selectedOptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("is_correct");
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error checking if answer is correct: " + e.getMessage(), e);
        }
        return false;
    }

}
