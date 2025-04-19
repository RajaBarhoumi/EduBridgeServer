package rmi;

import models.Certificate;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CertificateRemoteService extends Remote {
    void issueCertificate(int studentId, int testId, double score) throws RemoteException;
    Certificate getCertificateById(int id) throws RemoteException;
    List<Certificate> getCertificatesByStudentId(int studentId) throws RemoteException;
    void invalidateCertificate(int certificateId) throws RemoteException;
}
