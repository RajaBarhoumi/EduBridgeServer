package dao;

import models.Option;
import java.util.List;

public interface OptionDAO {
    void addOption(Option option) throws Exception;
    List<Option> getOptionsByQuestion(int questionId) throws Exception;
    void deleteOption(int id) throws Exception;
}
