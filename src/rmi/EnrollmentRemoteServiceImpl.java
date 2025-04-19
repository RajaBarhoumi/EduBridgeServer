package rmi;

import models.Enrollment;
import service.EnrollmentService;
import service.EnrollmentServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EnrollmentRemoteServiceImpl extends UnicastRemoteObject implements EnrollmentRemoteService {
    private final EnrollmentService enrollmentService;

    public EnrollmentRemoteServiceImpl() throws RemoteException {
        super();
        this.enrollmentService = new EnrollmentServiceImpl();
    }

    @Override
    public void enrollStudent(Enrollment enrollment) throws RemoteException {
        try {
            enrollmentService.enrollStudent(enrollment);
        } catch (Exception e) {
            throw new RemoteException("Error enrolling student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws RemoteException {
        try {
            return enrollmentService.getEnrollmentsByStudent(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching enrollments: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEnrollment(int id) throws RemoteException {
        try {
            enrollmentService.deleteEnrollment(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting enrollment: " + e.getMessage(), e);
        }
    }
}
