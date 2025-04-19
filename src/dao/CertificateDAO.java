package dao;

import models.Certificate;

import java.util.List;

public interface CertificateDAO {
    void issueCertificate(int studentId, int testId, double score) throws Exception;

    Certificate findById(int id) throws Exception;

    List<Certificate> findByStudentId(int studentId) throws Exception;

    void invalidateCertificate(int certificateId) throws Exception;
}
