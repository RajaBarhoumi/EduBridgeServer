package service;

import dao.TestStatisticsDAO;
import dao.TestStatisticsDAOImpl;
import models.TestStatistics;

public class TestStatisticsServiceImpl implements TestStatisticsService {

    private final TestStatisticsDAO dao;

    public TestStatisticsServiceImpl() throws Exception {
        dao = new TestStatisticsDAOImpl();
    }

    @Override
    public void addTestStatistics(TestStatistics stats) throws Exception {
        dao.insert(stats);
    }

    @Override
    public void updateTestStatistics(TestStatistics stats) throws Exception {
        dao.update(stats);
    }

    @Override
    public TestStatistics getTestStatisticsByTestId(int testId) throws Exception {
        return dao.findByTestId(testId);
    }
}
