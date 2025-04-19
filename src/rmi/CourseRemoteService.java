package rmi;

import models.Course;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CourseRemoteService extends Remote {
    void create(Course course) throws RemoteException;
    Course getById(int id) throws RemoteException;
    List<Course> getAll() throws RemoteException;
    void update(Course course) throws RemoteException;
    void delete(int id) throws RemoteException;
}
