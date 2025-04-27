package service;

import models.Option;
import models.Question;

import java.util.List;

public interface OptionService {
    void addOption(Option option) throws Exception;
    List<Option> getOptionsByQuestion(int questionId) throws Exception;
    void deleteOption(int id) throws Exception;

    void updateOption(Option option)throws Exception;
}
