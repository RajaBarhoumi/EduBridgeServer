package service;

import models.Course;
import java.util.List;

public interface CourseService {
    void create(Course course) throws Exception;
    Course getById(int id) throws Exception;
    List<Course> getAll() throws Exception;
    void update(Course course) throws Exception;
    void delete(int id) throws Exception;
    List<Course> getCoursesByProfessorId(int professorId) throws Exception;
    List<Course> getCoursesByStudentId(int studentId) throws Exception;
}
