package rmi;

import models.Answer;
import service.AnswerService;
import service.AnswerServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AnswerRemoteServiceImpl extends UnicastRemoteObject implements AnswerRemoteService {
    private final AnswerService answerService;

    public AnswerRemoteServiceImpl() throws RemoteException {
        super();
        this.answerService = new AnswerServiceImpl();
    }

    @Override
    public void saveAnswer(Answer answer) throws RemoteException {
        try {
            answerService.saveAnswer(answer);
        } catch (Exception e) {
            throw new RemoteException("Error saving answer: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws RemoteException {
        try {
            return answerService.getAnswersByStudentAndTest(studentId, testId);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving answers: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isAnswerCorrect(int selectedOptionId) throws RemoteException {
        try {
            return answerService.isAnswerCorrect(selectedOptionId);
        } catch (Exception e) {
            throw new RemoteException("Error checking if answer is correct: " + e.getMessage(), e);
        }
    }
}
