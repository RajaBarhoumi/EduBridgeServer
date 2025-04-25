package dao;

import models.User;

public interface UserDAO {
    void register(String name, String email, String password, String role) throws Exception;
    User login(String email, String password) throws Exception;
    User findById(int id) throws Exception;
    User findByEmail(String email) throws Exception;
    User getStudentByStudentTestId(int studentTestId) throws Exception;
}