package rmi;

import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import models.Question;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class QuestionRemoteServiceImpl extends UnicastRemoteObject implements QuestionRemoteService {
    private final QuestionDAO questionDAO;

    public QuestionRemoteServiceImpl() throws RemoteException {
        super();
        this.questionDAO = new QuestionDAOImpl();
    }

    @Override
    public void addQuestion(Question question) throws RemoteException {
        try {
            questionDAO.addQuestion(question);
        } catch (Exception e) {
            throw new RemoteException("Error adding question: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Question> getQuestionsByTest(int testId) throws RemoteException {
        try {
            return questionDAO.getQuestionsByTest(testId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching questions: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteQuestion(int id) throws RemoteException {
        try {
            questionDAO.deleteQuestion(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting question: " + e.getMessage(), e);
        }
    }

        @Override
        public void updateQuestion(Question question) throws RemoteException {
        try {
            questionDAO.updateQuestion(question);
        } catch (Exception e) {
            throw new RemoteException("Error updating test: " + e.getMessage(), e);
        }
    }
}
