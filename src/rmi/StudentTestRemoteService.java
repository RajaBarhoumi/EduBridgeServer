package rmi;

import models.StudentTest;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentTestRemoteService extends Remote {
    void addStudentTest(StudentTest studentTest) throws RemoteException;
    StudentTest getStudentTestById(int id) throws RemoteException;
    List<StudentTest> getStudentTestsByStudentId(int studentId) throws RemoteException;
    List<StudentTest> getStudentTestsByTestId(int testId) throws RemoteException;
    void updateStudentTest(StudentTest studentTest) throws RemoteException;
    void deleteStudentTest(int studentTestId) throws RemoteException;
}
