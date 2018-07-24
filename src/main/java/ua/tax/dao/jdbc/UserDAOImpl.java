package ua.tax.dao.jdbc;

import ua.tax.connection.ConnectionWrapper;
import ua.tax.connection.Transaction;
import ua.tax.dao.Mapper;
import ua.tax.dao.UserDAO;
import ua.tax.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
    private Properties sqlQuery;
    private Transaction transactionManager;

    public UserDAOImpl() {
        sqlQuery = Mapper.getProperties("sqlQuery");
        transactionManager = Transaction.getInstance();
    }

    @Override
    public void addUser(User user) throws SQLException {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQuery.getProperty("addUser");
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getUserRoleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getSQLState();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.getSQLState();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        List<User> allUsrs = new ArrayList<>();
        String sql = sqlQuery.getProperty("getAllUsers");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user.id");
                String firstName = resultSet.getString("user.first_name");
                String secondName = resultSet.getString("user.second_name");
                String email = resultSet.getString("user.email");
                String password = resultSet.getString("user.password");
                int userRoleId = resultSet.getInt("user.user_role_id");

                User user = new User.Builder()
                        .setId(id)
                        .setFirstName(firstName)
                        .setSecondName(secondName)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserRoleId(userRoleId)
                        .build();
                allUsrs.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsrs;
    }

    @Override
    public List<User> getUsersByRole(int roleId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        List<User> allUsersByRole = new ArrayList<>();
        String sql = sqlQuery.getProperty("getUsersByRole");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(6, roleId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user.id");
                String firstName = resultSet.getString("user.first_name");
                String secondName = resultSet.getString("user.second_name");
                String email = resultSet.getString("user.email");
                String password = resultSet.getString("user.password");

                User user = new User.Builder()
                        .setId(id)
                        .setFirstName(firstName)
                        .setSecondName(secondName)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserRoleId(roleId)
                        .build();

                allUsersByRole.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsersByRole;
    }

    @Override
    public User getUserByPasswordAndEmail(String password, String email) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        User user = null;
        String sql = sqlQuery.getProperty("getUserByPasswordAndEmail");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setString(4, email);
            statement.setString(5, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user.id");
                String firstNme = resultSet.getString("user.first_name");
                String secondName = resultSet.getString("user.second_name");
                int userRoleId = resultSet.getInt("user.user_role_id");
                user = new User.Builder()
                        .setId(id)
                        .setFirstName(firstNme)
                        .setSecondName(secondName)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserRoleId(userRoleId)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQuery.getProperty("updateUser");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getUserRoleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQuery.getProperty("deleteUser");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connectionWrapper.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
