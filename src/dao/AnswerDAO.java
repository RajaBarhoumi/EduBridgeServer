package dao;

import models.Answer;
import java.util.List;

public interface AnswerDAO {
    void saveAnswer(Answer answer) throws Exception;
    List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws Exception;
}
