package service;

import models.Enrollment;
import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Enrollment enrollment) throws Exception;
    List<Enrollment> getEnrollmentsByStudent(int studentId) throws Exception;
    void deleteEnrollment(int id) throws Exception;
    int getCourseCountByStudentId(int studentId) throws Exception;
}
