package service;

import dao.CertificateDAO;
import dao.CertificateDAOImpl;
import models.Certificate;

import java.util.List;

public class CertificateServiceImpl implements CertificateService {
    private final CertificateDAO certificateDAO;

    public CertificateServiceImpl() {
        this.certificateDAO = new CertificateDAOImpl();
    }

    @Override
    public void issueCertificate(int studentId, int testId, double score) throws Exception {
        if (score < 80) {
            throw new Exception("Score must be >= 80 to issue certificate");
        }
        certificateDAO.issueCertificate(studentId, testId, score);
    }

    @Override
    public Certificate getCertificateById(int id) throws Exception {
        Certificate certificate = certificateDAO.findById(id);
        if (certificate == null) {
            throw new Exception("Certificate not found with ID: " + id);
        }
        return certificate;
    }

    @Override
    public List<Certificate> getCertificatesByStudentId(int studentId) throws Exception {
        return certificateDAO.findByStudentId(studentId);
    }

    @Override
    public void invalidateCertificate(int certificateId) throws Exception {
        certificateDAO.invalidateCertificate(certificateId);
    }
}
