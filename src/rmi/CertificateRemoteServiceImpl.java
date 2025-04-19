package rmi;

import models.Certificate;
import service.CertificateService;
import service.CertificateServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CertificateRemoteServiceImpl extends UnicastRemoteObject implements CertificateRemoteService {
    private final CertificateService certificateService;

    public CertificateRemoteServiceImpl() throws RemoteException {
        super();
        this.certificateService = new CertificateServiceImpl();
    }

    @Override
    public void issueCertificate(int studentId, int testId, double score) throws RemoteException {
        try {
            certificateService.issueCertificate(studentId, testId, score);
        } catch (Exception e) {
            throw new RemoteException("Error issuing certificate: " + e.getMessage(), e);
        }
    }

    @Override
    public Certificate getCertificateById(int id) throws RemoteException {
        try {
            return certificateService.getCertificateById(id);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving certificate: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Certificate> getCertificatesByStudentId(int studentId) throws RemoteException {
        try {
            return certificateService.getCertificatesByStudentId(studentId);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving certificates: " + e.getMessage(), e);
        }
    }

    @Override
    public void invalidateCertificate(int certificateId) throws RemoteException {
        try {
            certificateService.invalidateCertificate(certificateId);
        } catch (Exception e) {
            throw new RemoteException("Error invalidating certificate: " + e.getMessage(), e);
        }
    }
}
