package rmi;

import models.Course;
import service.CourseService;
import service.CourseServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class CourseRemoteServiceImpl extends UnicastRemoteObject implements CourseRemoteService {
    private final CourseService courseService;

    public CourseRemoteServiceImpl() throws RemoteException {
        super();
        this.courseService = new CourseServiceImpl();
    }

    @Override
    public void create(Course course) throws RemoteException {
        try {
            courseService.create(course);
        } catch (Exception e) {
            throw new RemoteException("Error creating course: " + e.getMessage(), e);
        }
    }

    @Override
    public Course getById(int id) throws RemoteException {
        try {
            return courseService.getById(id);
        } catch (Exception e) {
            throw new RemoteException("Error fetching course: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> getAll() throws RemoteException {
        try {
            return courseService.getAll();
        } catch (Exception e) {
            throw new RemoteException("Error fetching courses: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Course course) throws RemoteException {
        try {
            courseService.update(course);
        } catch (Exception e) {
            throw new RemoteException("Error updating course: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws RemoteException {
        try {
            courseService.delete(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting course: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> getCoursesByProfessorId(int professorId) throws RemoteException {
        try {
            return courseService.getCoursesByProfessorId(professorId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching courses by professor: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId) throws RemoteException {
        try {
            return courseService.getCoursesByStudentId(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching courses by student: " + e.getMessage(), e);
        }
    }

    @Override
    public Course getCourseByTestId(int testId) throws RemoteException {
        try {
            return courseService.getCourseByTestId(testId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching course by test ID: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCourseCountByProfessorId(int professorId) throws RemoteException{
        try{
            return courseService.getCourseCountByProfessorId(professorId);
        }catch (Exception e){
            throw new RemoteException("Error fetching course count by professor: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Integer> getPassRateDistributionByCourse(int professorId) throws RemoteException{
        try {
            return courseService.getPassRateDistributionByCourse(professorId);
        }catch (Exception e){
            throw new RemoteException("Error fetching pass rate distribution by professor: " + e.getMessage(), e);
        }
    }


}
