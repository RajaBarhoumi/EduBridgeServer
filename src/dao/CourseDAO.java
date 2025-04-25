package dao;

import models.Course;
import java.util.List;
import java.util.Map;

public interface CourseDAO {
    void create(Course course) throws Exception;
    Course findById(int id) throws Exception;
    List<Course> findAll() throws Exception;
    void update(Course course) throws Exception;
    void delete(int id) throws Exception;
    List<Course> getCoursesByProfessorId(int professorId) throws Exception;
    List<Course> getCoursesByStudent(int studentId) throws Exception;
    Course getCourseByTestId(int testId) throws Exception;
    int getCourseCountByProfessorId(int professorId) throws Exception;
    Map<String, Integer> getPassRateDistributionByCourse(int professorId) throws Exception;
}
