package dao;

import models.Course;
import java.util.List;

public interface CourseDAO {
    void create(Course course) throws Exception;
    Course findById(int id) throws Exception;
    List<Course> findAll() throws Exception;
    void update(Course course) throws Exception;
    void delete(int id) throws Exception;
}
