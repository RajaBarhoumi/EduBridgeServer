package dao;

import models.StudentTest;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentTestDAOImpl implements StudentTestDAO {

    @Override
    public int addStudentTest(StudentTest studentTest) throws Exception {
        String sql = "INSERT INTO student_tests (student_id, test_id, score, passed, taken_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, studentTest.getStudentId());
            stmt.setInt(2, studentTest.getTestId());
            stmt.setFloat(3, studentTest.getScore());
            stmt.setBoolean(4, studentTest.isPassed());
            stmt.setTimestamp(5, studentTest.getTakenDate());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating student test failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating student test failed, no ID obtained.");
                }
            }
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
        String sql = "UPDATE student_tests SET score = ?, passed = ?, taken_date = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, studentTest.getScore());
            stmt.setBoolean(2, studentTest.isPassed());
            stmt.setTimestamp(3, studentTest.getTakenDate());
            stmt.setInt(4, studentTest.getId());
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

    @Override
    public String calculateAndUpdateStudentTestScore(int studentTestId) throws Exception {
        String sql = "SELECT * FROM answers WHERE student_test_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentTestId);

            try (ResultSet rs = stmt.executeQuery()) {
                int correctAnswers = 0;
                int totalQuestions = 0;
                float score = 0;

                while (rs.next()) {
                    int questionId = rs.getInt("question_id");
                    int selectedOptionId = rs.getInt("selected_option_id");

                    // Get correct option ID for the question
                    String optionSql = "SELECT id FROM options WHERE question_id = ? AND is_correct = TRUE";
                    try (PreparedStatement optionStmt = conn.prepareStatement(optionSql)) {
                        optionStmt.setInt(1, questionId);
                        try (ResultSet optionRs = optionStmt.executeQuery()) {
                            if (optionRs.next()) {
                                int correctOptionId = optionRs.getInt("id");
                                System.out.println("selected option: " + selectedOptionId);
                                System.out.println("correct option: " + correctOptionId);
                                // If student's answer is correct
                                if (selectedOptionId == correctOptionId) {
                                    correctAnswers++;
                                    System.out.println("Correct option "+correctAnswers);

                                    // Get the question points
                                    String questionSql = "SELECT points FROM questions WHERE id = ?";
                                    try (PreparedStatement questionStmt = conn.prepareStatement(questionSql)) {
                                        questionStmt.setInt(1, questionId);
                                        try (ResultSet questionRs = questionStmt.executeQuery()) {
                                            if (questionRs.next()) {
                                                score += questionRs.getInt("points");
                                            }
                                        }
                                    }
                                    System.out.println(score);
                                }
                                totalQuestions++;
                            }
                        }
                    }
                }

                boolean passed = false;
                if (totalQuestions > 0 && (correctAnswers * 100.0 / totalQuestions) >= 80) {
                    passed = true;
                }

                // Update student_test with score and pass status
                String updateSql = "UPDATE student_tests SET score = ?, passed = ? WHERE id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setFloat(1, score);
                    updateStmt.setBoolean(2, passed);
                    updateStmt.setInt(3, studentTestId);
                    updateStmt.executeUpdate();
                }

                if (passed) {
                    return "Passed with score: " + score;
                } else {
                    return "Failed with score: " + score;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error calculating and updating student test score: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCertificateCountByProfessorId(int professorId) throws Exception {
        int count = 0;
        String query = "SELECT COUNT(*) FROM student_tests st " +
                "JOIN tests t ON st.test_id = t.id " +
                "JOIN courses c ON t.course_id = c.id " +
                "WHERE c.professor_id = ? AND st.passed = 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, professorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int getTestCountByStudentId(int studentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM student_tests WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting tests by student ID: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCertificateCountByStudentId(int studentId) throws Exception {
        String sql = "SELECT COUNT(*) FROM student_tests WHERE student_id = ? AND passed = 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error counting certificates by student ID: " + e.getMessage(), e);
        }
    }

}
