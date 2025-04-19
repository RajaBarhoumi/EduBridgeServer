package rmi;

import models.Course;
import service.CourseService;
import service.CourseServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
}
