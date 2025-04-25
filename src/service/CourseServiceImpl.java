package service;

import dao.CourseDAO;
import dao.CourseDAOImpl;
import models.Course;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Course> getCoursesByProfessorId(int professorId) throws Exception {
        return courseDAO.getCoursesByProfessorId(professorId);
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId) throws Exception {
        return courseDAO.getCoursesByStudent(studentId);
    }

    @Override
    public Course getCourseByTestId(int testId) throws Exception {
        return courseDAO.getCourseByTestId(testId);
    }

    @Override
    public int getCourseCountByProfessorId(int professorId) throws Exception {
        return courseDAO.getCourseCountByProfessorId(professorId);
    }

    @Override
    public Map<String, Integer> getPassRateDistributionByCourse(int professorId) throws Exception{
        return courseDAO.getPassRateDistributionByCourse(professorId);
    }
}
