package service;

import models.Question;
import java.util.List;

public interface QuestionService {
    void addQuestion(Question question) throws Exception;
    List<Question> getQuestionsByTest(int testId) throws Exception;
    void deleteQuestion(int id) throws Exception;
}
