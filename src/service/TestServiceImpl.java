package service;

import dao.TestDAO;
import dao.TestDAOImpl;
import models.Test;

import java.util.List;

public class TestServiceImpl implements TestService {
    private final TestDAO testDAO;

    public TestServiceImpl() {
        this.testDAO = new TestDAOImpl();
    }

    @Override
    public void addTest(Test test) throws Exception {
        testDAO.addTest(test);
    }

    @Override
    public Test getTestById(int id) throws Exception {
        return testDAO.findById(id);
    }

    @Override
    public List<Test> getTestsByStudentId(int studentId) throws Exception {
        return testDAO.findByStudentId(studentId);
    }

    @Override
    public List<Test> getTestsByCourseId(int courseId) throws Exception {
        return testDAO.findByCourseId(courseId);
    }

    @Override
    public List<Test> getTestsByProfessorId(int professorId) throws Exception {
        return testDAO.findByProfessorId(professorId);
    }

    @Override
    public void updateTest(Test test) throws Exception {
        testDAO.updateTest(test);
    }

    @Override
    public void deleteTest(int testId) throws Exception {
        testDAO.deleteTest(testId);
    }

    @Override
    public int getNumberOfQuestions(int testId) throws Exception {
        return testDAO.getNumberOfQuestions(testId);
    }

    @Override
    public int getTestCountByProfessorId(int professorId) throws Exception{
        return testDAO.getTestCountByProfessorId(professorId);
    }
}
