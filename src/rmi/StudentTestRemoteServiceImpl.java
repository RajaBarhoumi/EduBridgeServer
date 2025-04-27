package rmi;

import dao.StudentTestDAO;
import dao.StudentTestDAOImpl;
import models.StudentTest;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class StudentTestRemoteServiceImpl extends UnicastRemoteObject implements StudentTestRemoteService {

    private final StudentTestDAO studentTestDAO;

    public StudentTestRemoteServiceImpl() throws RemoteException {
        studentTestDAO = new StudentTestDAOImpl();
    }

    @Override
    public int addStudentTest(StudentTest studentTest) throws RemoteException {
        try {
            return studentTestDAO.addStudentTest(studentTest);
        } catch (Exception e) {
            throw new RemoteException("Error adding student test: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentTest getStudentTestById(int id) throws RemoteException {
        try {
            return studentTestDAO.findById(id);
        } catch (Exception e) {
            throw new RemoteException("Error getting student test by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentTest> getStudentTestsByStudentId(int studentId) throws RemoteException {
        try {
            return studentTestDAO.findByStudentId(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error getting student tests by student ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StudentTest> getStudentTestsByTestId(int testId) throws RemoteException {
        try {
            return studentTestDAO.findByTestId(testId);
        } catch (Exception e) {
            throw new RemoteException("Error getting student tests by test ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStudentTest(StudentTest studentTest) throws RemoteException {
        try {
            studentTestDAO.updateStudentTest(studentTest);
        } catch (Exception e) {
            throw new RemoteException("Error updating student test: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudentTest(int studentTestId) throws RemoteException {
        try {
            studentTestDAO.deleteStudentTest(studentTestId);
        } catch (Exception e) {
            throw new RemoteException("Error deleting student test: " + e.getMessage(), e);
        }
    }

    @Override
    public String calculateAndUpdateStudentTestScore(int studentTestId) throws RemoteException {
        try {
            return studentTestDAO.calculateAndUpdateStudentTestScore(studentTestId);
        } catch (Exception e) {
            throw new RemoteException("Error calculating and updating student test score: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCertificateCountByProfessorId(int professorId) throws RemoteException{
        try{
            return studentTestDAO.getCertificateCountByProfessorId(professorId);
        }catch (Exception e){
            throw new RemoteException("Error getting certificate count: " + e.getMessage(), e);
        }
    }

    @Override
    public int getTestCountByStudentId(int studentId) throws RemoteException {
        try{
            return studentTestDAO.getTestCountByStudentId(studentId);
        }catch (Exception e){
            throw new RemoteException("Error getting test count: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCertificateCountByStudentId(int studentId) throws RemoteException {
        try{
            return studentTestDAO.getCertificateCountByStudentId(studentId);
        }catch (Exception e){
            throw new RemoteException("Error getting certificate count: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, Object>> getStudentTestResults(int studentId) throws RemoteException {
        try{
            return studentTestDAO.getStudentTestResults(studentId);
        }catch (Exception e){
            throw new RemoteException("Error getting test results: " + e.getMessage(), e);
        }
    }
}
