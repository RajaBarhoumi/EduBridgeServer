package rmi;

import dao.AnswerDAO;
import dao.AnswerDAOImpl;
import models.Answer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AnswerRemoteServiceImpl extends UnicastRemoteObject implements AnswerRemoteService {
    private final AnswerDAO answerDAO;

    public AnswerRemoteServiceImpl() throws RemoteException {
        super();
        this.answerDAO = new AnswerDAOImpl();
    }

    @Override
    public void saveAnswer(Answer answer) throws RemoteException {
        try {
            answerDAO.saveAnswer(answer);
        } catch (Exception e) {
            throw new RemoteException("Error saving answer: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws RemoteException {
        try {
            return answerDAO.getAnswersByStudentAndTest(studentId, testId);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving answers: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isAnswerCorrect(int selectedOptionId) throws RemoteException {
        try {
            return answerDAO.isAnswerCorrect(selectedOptionId);
        } catch (Exception e) {
            throw new RemoteException("Error checking if answer is correct: " + e.getMessage(), e);
        }
    }
}
