package rmi;

import dao.EnrollmentDAO;
import dao.EnrollmentDAOImpl;
import models.Enrollment;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EnrollmentRemoteServiceImpl extends UnicastRemoteObject implements EnrollmentRemoteService {
    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentRemoteServiceImpl() throws RemoteException {
        super();
        this.enrollmentDAO = new EnrollmentDAOImpl();
    }

    @Override
    public void enrollStudent(Enrollment enrollment) throws RemoteException {
        try {
            enrollmentDAO.enrollStudent(enrollment);
        } catch (Exception e) {
            throw new RemoteException("Error enrolling student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws RemoteException {
        try {
            return enrollmentDAO.getEnrollmentsByStudent(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching enrollments: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteEnrollment(int id) throws RemoteException {
        try {
            enrollmentDAO.deleteEnrollment(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting enrollment: " + e.getMessage(), e);
        }
    }

    @Override
    public int getCourseCountByStudentId(int studentId) throws RemoteException{
        try {
            return enrollmentDAO.getCourseCountByStudentId(studentId);
        }catch (Exception e){
            throw new RemoteException("Error fetching course count: " + e.getMessage(), e);
        }
    }
}
