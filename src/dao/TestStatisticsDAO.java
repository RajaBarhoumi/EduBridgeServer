package dao;

import models.TestStatistics;

public interface TestStatisticsDAO {
    void insert(TestStatistics stats) throws Exception;
    void update(TestStatistics stats) throws Exception;
    TestStatistics findByTestId(int testId) throws Exception;
}
