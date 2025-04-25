package service;

import models.StudentTest;

import java.util.List;

public interface StudentTestService {
    int addStudentTest(StudentTest studentTest) throws Exception;
    StudentTest getStudentTestById(int id) throws Exception;
    List<StudentTest> getStudentTestsByStudentId(int studentId) throws Exception;
    List<StudentTest> getStudentTestsByTestId(int testId) throws Exception;
    void updateStudentTest(StudentTest studentTest) throws Exception;
    void deleteStudentTest(int studentTestId) throws Exception;
    String calculateAndUpdateStudentTestScore(int studentTestId) throws Exception;
    int getCertificateCountByProfessorId(int professorId) throws Exception;
    int getTestCountByStudentId(int studentId) throws Exception;
    int getCertificateCountByStudentId(int studentId) throws Exception;
}
