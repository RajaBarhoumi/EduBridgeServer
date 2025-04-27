package rmi;

import dao.TestStatisticsDAO;
import dao.TestStatisticsDAOImpl;
import models.TestStatistics;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestStatisticsRemoteServiceImpl extends UnicastRemoteObject implements TestStatisticsRemoteService {

    private final TestStatisticsDAO testStatisticsDAO;

    public TestStatisticsRemoteServiceImpl() throws Exception {
        super();
            this.testStatisticsDAO = new TestStatisticsDAOImpl();
    }

    @Override
    public void addTestStatistics(TestStatistics stats) throws RemoteException {
        try {
            testStatisticsDAO.insert(stats);
        } catch (Exception e) {
            throw new RemoteException("Error adding test statistics", e);
        }
    }

    @Override
    public void updateTestStatistics(TestStatistics stats) throws RemoteException {
        try {
            testStatisticsDAO.update(stats);
        } catch (Exception e) {
            throw new RemoteException("Error updating test statistics", e);
        }
    }

    @Override
    public TestStatistics getTestStatisticsByTestId(int testId) throws RemoteException {
        try {
            return testStatisticsDAO.findByTestId(testId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching test statistics", e);
        }
    }
}
