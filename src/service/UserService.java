package service;

import models.User;

public interface UserService {
    void register(String name, String email, String password, String role) throws Exception;
    User login(String email, String password) throws Exception;
    User getUserById(int id) throws Exception;
    void validateRole(int userId, String requiredRole) throws Exception;
    User getStudentByStudentTestId(int studentTestId) throws Exception;
}