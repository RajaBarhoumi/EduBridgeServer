package service;

import dao.OptionDAO;
import dao.OptionDAOImpl;
import models.Option;

import java.util.List;

public class OptionServiceImpl implements OptionService {
    private final OptionDAO optionDAO;

    public OptionServiceImpl() {
        this.optionDAO = new OptionDAOImpl();
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
}
