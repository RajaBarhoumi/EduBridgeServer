package service;

import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import models.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDAO questionDAO = new QuestionDAOImpl();

    @Override
    public void addQuestion(Question question) throws Exception {
        questionDAO.addQuestion(question);
    }

    @Override
    public List<Question> getQuestionsByTest(int testId) throws Exception {
        return questionDAO.getQuestionsByTest(testId);
    }

    @Override
    public void deleteQuestion(int id) throws Exception {
        questionDAO.deleteQuestion(id);
    }
}
