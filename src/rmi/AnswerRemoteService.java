package rmi;

import models.Answer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AnswerRemoteService extends Remote {
    void saveAnswer(Answer answer) throws RemoteException;
    List<Answer> getAnswersByStudentAndTest(int studentId, int testId) throws RemoteException;
}
