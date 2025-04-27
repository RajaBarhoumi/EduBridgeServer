package rmi;

import dao.OptionDAO;
import dao.OptionDAOImpl;
import models.Option;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OptionRemoteServiceImpl extends UnicastRemoteObject implements OptionRemoteService {
    private final OptionDAO optionDAO;

    public OptionRemoteServiceImpl() throws RemoteException {
        super();
        this.optionDAO = new OptionDAOImpl();
    }

    @Override
    public void addOption(Option option) throws RemoteException {
        try {
            optionDAO.addOption(option);
        } catch (Exception e) {
            throw new RemoteException("Error adding option: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Option> getOptionsByQuestion(int questionId) throws RemoteException {
        try {
            return optionDAO.getOptionsByQuestion(questionId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching options: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteOption(int id) throws RemoteException {
        try {
            optionDAO.deleteOption(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting option: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateOption(Option option) throws RemoteException {
        try {
            optionDAO.updateOption(option);
        } catch (Exception e) {
            throw new RemoteException("Error updating option: " + e.getMessage(), e);
        }
    }
}
