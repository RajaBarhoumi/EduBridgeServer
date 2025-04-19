package service;

import dao.StudentTestDAO;
import dao.StudentTestDAOImpl;
import models.StudentTest;

import java.util.List;

public class StudentTestServiceImpl implements StudentTestService {
    private final StudentTestDAO studentTestDAO;

    public StudentTestServiceImpl() {
        this.studentTestDAO = new StudentTestDAOImpl();
    }

    @Override
    public void addStudentTest(StudentTest studentTest) throws Exception {
        studentTestDAO.addStudentTest(studentTest);
    }

    @Override
    public StudentTest getStudentTestById(int id) throws Exception {
        return studentTestDAO.findById(id);
    }

    @Override
    public List<StudentTest> getStudentTestsByStudentId(int studentId) throws Exception {
        return studentTestDAO.findByStudentId(studentId);
    }

    @Override
    public List<StudentTest> getStudentTestsByTestId(int testId) throws Exception {
        return studentTestDAO.findByTestId(testId);
    }

    @Override
    public void updateStudentTest(StudentTest studentTest) throws Exception {
        studentTestDAO.updateStudentTest(studentTest);
    }

    @Override
    public void deleteStudentTest(int studentTestId) throws Exception {
        studentTestDAO.deleteStudentTest(studentTestId);
    }
}
