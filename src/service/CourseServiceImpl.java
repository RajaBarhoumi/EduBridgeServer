package service;

import dao.CourseDAO;
import dao.CourseDAOImpl;
import models.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseDAO courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public void create(Course course) throws Exception {
        courseDAO.create(course);
    }

    @Override
    public Course getById(int id) throws Exception {
        return courseDAO.findById(id);
    }

    @Override
    public List<Course> getAll() throws Exception {
        return courseDAO.findAll();
    }

    @Override
    public void update(Course course) throws Exception {
        courseDAO.update(course);
    }

    @Override
    public void delete(int id) throws Exception {
        courseDAO.delete(id);
    }
}
