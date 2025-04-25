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
    public int addStudentTest(StudentTest studentTest) throws Exception {
        return studentTestDAO.addStudentTest(studentTest);
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

    @Override
    public String calculateAndUpdateStudentTestScore(int studentTestId) throws Exception {
        return studentTestDAO.calculateAndUpdateStudentTestScore(studentTestId);
    }

    @Override
    public int getCertificateCountByProfessorId(int professorId) throws Exception{
        return studentTestDAO.getCertificateCountByProfessorId(professorId);
    }
}
