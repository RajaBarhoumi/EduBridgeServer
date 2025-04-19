package rmi;

import models.StudentTest;
import service.StudentTestService;
import service.StudentTestServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class StudentTestRemoteServiceImpl extends UnicastRemoteObject implements StudentTestRemoteService {

    private final StudentTestService studentTestService;

    public StudentTestRemoteServiceImpl() throws RemoteException {
        studentTestService = new StudentTestServiceImpl();
    }

    @Override
    public void addStudentTest(StudentTest studentTest) throws RemoteException {
        try {
            studentTestService.addStudentTest(studentTest);
        } catch (Exception e) {
            throw new RemoteException("Error adding student test: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentTest getStudentTestById(int id) throws RemoteException {
        try {
            return studentTestService.getStudentTestById(id);
        } catch (Exception e) {
            throw new RemoteException("Error getting student test by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentTest> getStudentTestsByStudentId(int studentId) throws RemoteException {
        try {
            return studentTestService.getStudentTestsByStudentId(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error getting student tests by student ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentTest> getStudentTestsByTestId(int testId) throws RemoteException {
        try {
            return studentTestService.getStudentTestsByTestId(testId);
        } catch (Exception e) {
            throw new RemoteException("Error getting student tests by test ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStudentTest(StudentTest studentTest) throws RemoteException {
        try {
            studentTestService.updateStudentTest(studentTest);
        } catch (Exception e) {
            throw new RemoteException("Error updating student test: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudentTest(int studentTestId) throws RemoteException {
        try {
            studentTestService.deleteStudentTest(studentTestId);
        } catch (Exception e) {
            throw new RemoteException("Error deleting student test: " + e.getMessage(), e);
        }
    }
}
