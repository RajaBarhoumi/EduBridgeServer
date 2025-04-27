package rmi;

import dao.UserDAO;
import dao.UserDAOImpl;
import models.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserRemoteServiceImpl extends UnicastRemoteObject implements UserRemoteService {
    private final UserDAO userDAO;

    public UserRemoteServiceImpl() throws RemoteException {
        super();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void register(String name, String email, String password, String role) throws RemoteException {
        try {
            userDAO.register(name, email, password, role);
        } catch (Exception e) {
            throw new RemoteException("Error registering user: " + e.getMessage(), e);
        }
    }

    @Override
    public User login(String email, String password) throws RemoteException {
        try {
            return userDAO.login(email, password);
        } catch (Exception e) {
            throw new RemoteException("Error logging in: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        try {
            return userDAO.findById(id);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving user: " + e.getMessage(), e);
        }
    }

    @Override
    public User getStudentByStudentTestId(int studentTestId) throws RemoteException {
        try {
            return userDAO.getStudentByStudentTestId(studentTestId);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving student by StudentTest ID: " + e.getMessage(), e);
        }
    }
}