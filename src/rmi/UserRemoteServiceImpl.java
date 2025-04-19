package rmi;

import models.User;
import service.UserService;
import service.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserRemoteServiceImpl extends UnicastRemoteObject implements UserRemoteService {
    private final UserService userService;

    public UserRemoteServiceImpl() throws RemoteException {
        super();
        this.userService = new UserServiceImpl();
    }

    @Override
    public void register(String name, String email, String password, String role) throws RemoteException {
        try {
            userService.register(name, email, password, role);
        } catch (Exception e) {
            throw new RemoteException("Error registering user: " + e.getMessage(), e);
        }
    }

    @Override
    public User login(String email, String password) throws RemoteException {
        try {
            return userService.login(email, password);
        } catch (Exception e) {
            throw new RemoteException("Error logging in: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        try {
            return userService.getUserById(id);
        } catch (Exception e) {
            throw new RemoteException("Error retrieving user: " + e.getMessage(), e);
        }
    }
}