package rmi;

import models.Option;
import models.Question;
import service.OptionService;
import service.OptionServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OptionRemoteServiceImpl extends UnicastRemoteObject implements OptionRemoteService {
    private final OptionService optionService;

    public OptionRemoteServiceImpl() throws RemoteException {
        super();
        this.optionService = new OptionServiceImpl();
    }

    @Override
    public void addOption(Option option) throws RemoteException {
        try {
            optionService.addOption(option);
        } catch (Exception e) {
            throw new RemoteException("Error adding option: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Option> getOptionsByQuestion(int questionId) throws RemoteException {
        try {
            return optionService.getOptionsByQuestion(questionId);
        } catch (Exception e) {
            throw new RemoteException("Error fetching options: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteOption(int id) throws RemoteException {
        try {
            optionService.deleteOption(id);
        } catch (Exception e) {
            throw new RemoteException("Error deleting option: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateOption(Option option) throws RemoteException {
        try {
            optionService.updateOption(option);
        } catch (Exception e) {
            throw new RemoteException("Error updating option: " + e.getMessage(), e);
        }
    }
}
