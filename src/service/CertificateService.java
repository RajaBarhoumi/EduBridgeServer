package service;

import models.Certificate;

import java.util.List;

public interface CertificateService {
    void issueCertificate(int studentId, int testId, double score) throws Exception;
    Certificate getCertificateById(int id) throws Exception;
    List<Certificate> getCertificatesByStudentId(int studentId) throws Exception;
    void invalidateCertificate(int certificateId) throws Exception;
}
