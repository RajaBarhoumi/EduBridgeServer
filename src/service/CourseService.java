package service;

import models.Course;
import java.util.List;
import java.util.Map;

public interface CourseService {
    void create(Course course) throws Exception;
    Course getById(int id) throws Exception;
    List<Course> getAll() throws Exception;
    void update(Course course) throws Exception;
    void delete(int id) throws Exception;
    List<Course> getCoursesByProfessorId(int professorId) throws Exception;
    List<Course> getCoursesByStudentId(int studentId) throws Exception;
    Course getCourseByTestId(int testId) throws Exception;
    int getCourseCountByProfessorId(int professorId) throws Exception;
    Map<String, Integer> getPassRateDistributionByCourse(int professorId) throws Exception;
}
