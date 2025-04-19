package rmi;

import models.Question;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuestionRemoteService extends Remote {
    void addQuestion(Question question) throws RemoteException;
    List<Question> getQuestionsByTest(int testId) throws RemoteException;
    void deleteQuestion(int id) throws RemoteException;
}
