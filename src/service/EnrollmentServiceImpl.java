package service;

import dao.EnrollmentDAO;
import dao.EnrollmentDAOImpl;
import models.Enrollment;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentServiceImpl() {
        this.enrollmentDAO = new EnrollmentDAOImpl();
    }

    @Override
    public void enrollStudent(Enrollment enrollment) throws Exception {
        enrollmentDAO.enrollStudent(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) throws Exception {
        return enrollmentDAO.getEnrollmentsByStudent(studentId);
    }

    @Override
    public void deleteEnrollment(int id) throws Exception {
        enrollmentDAO.deleteEnrollment(id);
    }
}
