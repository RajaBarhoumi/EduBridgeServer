package rmi;

import models.Test;
import service.TestService;
import service.TestServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TestRemoteServiceImpl extends UnicastRemoteObject implements TestRemoteService {
    private final TestService testService;

    public TestRemoteServiceImpl() throws RemoteException {
        super();
        this.testService = new TestServiceImpl();
    }

    @Override
    public void addTest(Test test) throws RemoteException {
        try {
            testService.addTest(test);
        } catch (Exception e) {
            throw new RemoteException("Error adding test: " + e.getMessage(), e);
        }
    }

    @Override
    public Test getTestById(int id) throws RemoteException {
        try {
            return testService.getTestById(id);
        } catch (Exception e) {
            throw new RemoteException("Error getting test by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Test> getTestsByStudentId(int studentId) throws RemoteException {
        try {
            return testService.getTestsByStudentId(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error getting tests by student ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Test> getTestsByCourseId(int courseId) throws RemoteException {
        try {
            return testService.getTestsByCourseId(courseId);
        } catch (Exception e) {
            throw new RemoteException("Error getting tests by course ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Test> getTestsByProfessorId(int professorId) throws RemoteException {
        try {
            return testService.getTestsByProfessorId(professorId);
        } catch (Exception e) {
            throw new RemoteException("Error getting tests by professor ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateTest(Test test) throws RemoteException {
        try {
            testService.updateTest(test);
        } catch (Exception e) {
            throw new RemoteException("Error updating test: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTest(int testId) throws RemoteException {
        try {
            testService.deleteTest(testId);
        } catch (Exception e) {
            throw new RemoteException("Error deleting test: " + e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfQuestions(int testId) throws RemoteException {
        try {
            return testService.getNumberOfQuestions(testId);
        } catch (Exception e) {
            throw new RemoteException("Error getting number of questions: " + e.getMessage(), e);
        }
    }

    @Override
    public int getTestCountByProfessorId(int professorId) throws RemoteException{
        try {
            return testService.getTestCountByProfessorId(professorId);
        }catch (Exception e){
            throw new RemoteException("Error getting tests by professor ID: " + e.getMessage(), e);
        }
    }
}
