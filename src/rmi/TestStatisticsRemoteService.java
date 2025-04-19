package rmi;

import models.TestStatistics;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestStatisticsRemoteService extends Remote {
    void addTestStatistics(TestStatistics stats) throws RemoteException;
    void updateTestStatistics(TestStatistics stats) throws RemoteException;
    TestStatistics getTestStatisticsByTestId(int testId) throws RemoteException;
}
