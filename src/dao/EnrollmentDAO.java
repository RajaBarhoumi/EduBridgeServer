package dao;

import models.Enrollment;
import java.util.List;

public interface EnrollmentDAO {
    void enrollStudent(Enrollment enrollment) throws Exception;
    List<Enrollment> getEnrollmentsByStudent(int studentId) throws Exception;
    void deleteEnrollment(int id) throws Exception;
}
