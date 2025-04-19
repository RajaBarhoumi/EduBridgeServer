package service;

import models.Answer;
import java.util.List;

public interface AnswerService {
    void saveAnswer(Answer answer) throws Exception;
    List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws Exception;
}
