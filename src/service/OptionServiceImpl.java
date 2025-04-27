package service;

import dao.OptionDAO;
import dao.OptionDAOImpl;
import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import models.Option;
import models.Question;

import java.util.List;

public class OptionServiceImpl implements OptionService {
    private final OptionDAO optionDAO;
    private final QuestionDAO questionDAO;

    public OptionServiceImpl() {
        this.optionDAO = new OptionDAOImpl();
        this.questionDAO = new QuestionDAOImpl();
    }

    @Override
    public void addOption(Option option) throws Exception {
        optionDAO.addOption(option);
    }

    @Override
    public List<Option> getOptionsByQuestion(int questionId) throws Exception {
        return optionDAO.getOptionsByQuestion(questionId);
    }

    @Override
    public void deleteOption(int id) throws Exception {
        optionDAO.deleteOption(id);
    }

    @Override
    public void updateOption(Option option)throws Exception {
        optionDAO.updateOption(option);
    }
}
