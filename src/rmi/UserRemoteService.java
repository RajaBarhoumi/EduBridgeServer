package rmi;

import models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserRemoteService extends Remote {
    void register(String name, String email, String password, String role) throws RemoteException;
    User login(String email, String password) throws RemoteException;
    User getUserById(int id) throws RemoteException;
}