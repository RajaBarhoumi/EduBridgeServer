package rmi;

import models.Question;
import service.QuestionService;
import service.QuestionServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuestionRemoteServiceImpl extends UnicastRemoteObject implements QuestionRemoteService {
    private final QuestionService questionService;

    public QuestionRemoteServiceImpl() throws RemoteException {
        super();
        this.questionService = new QuestionServiceImpl();
    }

    @Override
    public void addQuestion(Question question) throws RemoteException {
        try {
            questionService.addQuestion(question);
        } catch (Exception e) {
            throw new RemoteException("Error adding question: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Question> getQuestionsByTest(int testId) throws RemoteException {
        try {
            return questionService.getQuestionsByTest(testId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching questions: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteQuestion(int id) throws RemoteException {
        try {
            questionService.deleteQuestion(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting question: " + e.getMessage(), e);
        }
    }
}
