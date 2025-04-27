package dao;

import models.Question;
import models.Test;

import java.util.List;

public interface QuestionDAO {
    void addQuestion(Question question) throws Exception;
    List<Question> getQuestionsByTest(int testId) throws Exception;
    void deleteQuestion(int id) throws Exception;
    void updateQuestion(Question question) throws Exception;

}
