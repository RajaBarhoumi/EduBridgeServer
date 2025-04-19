package rmi;

import models.Test;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TestRemoteService extends Remote {
    void addTest(Test test) throws RemoteException;
    Test getTestById(int id) throws RemoteException;
    List<Test> getTestsByCourseId(int courseId) throws RemoteException;
    List<Test> getTestsByProfessorId(int professorId) throws RemoteException;
    void updateTest(Test test) throws RemoteException;
    void deleteTest(int testId) throws RemoteException;
}
