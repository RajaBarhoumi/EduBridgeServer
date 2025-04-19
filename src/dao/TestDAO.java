package dao;

import models.Test;

import java.util.List;

public interface TestDAO {
    void addTest(Test test) throws Exception;
    Test findById(int id) throws Exception;
    List<Test> findByCourseId(int courseId) throws Exception;
    List<Test> findByProfessorId(int professorId) throws Exception;
    void updateTest(Test test) throws Exception;
    void deleteTest(int testId) throws Exception;
}
