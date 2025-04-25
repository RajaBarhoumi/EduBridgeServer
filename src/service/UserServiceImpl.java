package service;

import dao.UserDAO;
import dao.UserDAOImpl;
import models.User;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void register(String name, String email, String password, String role) throws Exception {
        // Validate role
        if (!role.equals("student") && !role.equals("professor")) {
            throw new Exception("Invalid role: must be 'student' or 'professor'");
        }

        // Check if email is already taken
        if (userDAO.findByEmail(email) != null) {
            throw new Exception("Email already exists");
        }

        // Register the user
        userDAO.register(name, email, password, role);
    }

    @Override
    public User login(String email, String password) throws Exception {
        return userDAO.login(email, password);
    }

    @Override
    public User getUserById(int id) throws Exception {
        User user = userDAO.findById(id);
        if (user == null) {
            throw new Exception("User not found with ID: " + id);
        }
        return user;
    }

    @Override
    public void validateRole(int userId, String requiredRole) throws Exception {
        User user = getUserById(userId);
        if (!user.getRole().toString().equals(requiredRole)) {
            throw new Exception("Unauthorized: User must be a " + requiredRole);
        }
    }

    @Override
    public User getStudentByStudentTestId(int studentTestId) throws Exception {
        User user = userDAO.getStudentByStudentTestId(studentTestId);
        if (user == null) {
            throw new Exception("No student found for StudentTest ID: " + studentTestId);
        }
        return user;
    }
}