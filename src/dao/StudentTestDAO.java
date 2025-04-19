package dao;

import models.StudentTest;

import java.util.List;

public interface StudentTestDAO {
    void addStudentTest(StudentTest studentTest) throws Exception;
    StudentTest findById(int id) throws Exception;
    List<StudentTest> findByStudentId(int studentId) throws Exception;
    List<StudentTest> findByTestId(int testId) throws Exception;
    void updateStudentTest(StudentTest studentTest) throws Exception;
    void deleteStudentTest(int studentTestId) throws Exception;
}
