package service;

import dao.AnswerDAO;
import dao.AnswerDAOImpl;
import models.Answer;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDAO answerDAO = new AnswerDAOImpl();

    @Override
    public void saveAnswer(Answer answer) throws Exception {
        answerDAO.saveAnswer(answer);
    }

    @Override
    public List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws Exception {
        return answerDAO.getAnswersByStudentAndTest(studentId, testId);
    }
}
