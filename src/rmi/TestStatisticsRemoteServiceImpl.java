package rmi;

import models.TestStatistics;
import service.TestStatisticsService;
import service.TestStatisticsServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestStatisticsRemoteServiceImpl extends UnicastRemoteObject implements TestStatisticsRemoteService {

    private final TestStatisticsService service;

    public TestStatisticsRemoteServiceImpl() throws RemoteException {
        try {
            service = new TestStatisticsServiceImpl();
        } catch (Exception e) {
            throw new RemoteException("Service initialization failed", e);
        }
    }

    @Override
    public void addTestStatistics(TestStatistics stats) throws RemoteException {
        try {
            service.addTestStatistics(stats);
        } catch (Exception e) {
            throw new RemoteException("Error adding test statistics", e);
        }
    }

    @Override
    public void updateTestStatistics(TestStatistics stats) throws RemoteException {
        try {
            service.updateTestStatistics(stats);
        } catch (Exception e) {
            throw new RemoteException("Error updating test statistics", e);
        }
    }

    @Override
    public TestStatistics getTestStatisticsByTestId(int testId) throws RemoteException {
        try {
            return service.getTestStatisticsByTestId(testId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching test statistics", e);
        }
    }
}
