package rmi;

import models.Enrollment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EnrollmentRemoteService extends Remote {
    void enrollStudent(Enrollment enrollment) throws RemoteException;
    List<Enrollment> getEnrollmentsByStudent(int studentId) throws RemoteException;
    void deleteEnrollment(int id) throws RemoteException;
}
