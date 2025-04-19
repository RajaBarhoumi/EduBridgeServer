package service;

import models.TestStatistics;

public interface TestStatisticsService {
    void addTestStatistics(TestStatistics stats) throws Exception;
    void updateTestStatistics(TestStatistics stats) throws Exception;
    TestStatistics getTestStatisticsByTestId(int testId) throws Exception;
}
