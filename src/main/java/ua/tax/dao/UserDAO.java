package ua.tax.dao;

import ua.tax.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    //create
    void addUser(User user) throws SQLException;

    //read
    List<User> getAllUsers();
    List<User> getUsersByRole(int roleId);
    User getUserByPasswordAndEmail(String password, String email);

    //update
    void updateUser(User user);

    //delete
    void deleteUser(int id);
}
