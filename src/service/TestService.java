package service;

import models.Test;

import java.util.List;

public interface TestService {
    void addTest(Test test) throws Exception;
    Test getTestById(int id) throws Exception;
    List<Test> getTestsByCourseId(int courseId) throws Exception;
    List<Test> getTestsByProfessorId(int professorId) throws Exception;
    void updateTest(Test test) throws Exception;
    void deleteTest(int testId) throws Exception;
    List<Test> getTestsByStudentId(int studentId) throws Exception;
    int getNumberOfQuestions(int testId) throws Exception;
    int getTestCountByProfessorId(int professorId) throws Exception;
}
