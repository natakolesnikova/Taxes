package ua.tax.dao.jdbc;

import ua.tax.connection.ConnectionWrapper;
import ua.tax.connection.Transaction;
import ua.tax.dao.InspectorDAO;
import ua.tax.dao.Mapper;
import ua.tax.entity.Inspector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class InspectorDAOImpl implements InspectorDAO {
    private Properties sqlQueries;
    private Transaction transactionManager;

    public InspectorDAOImpl() {
        sqlQueries = Mapper.getProperties("sqlQueries");
        transactionManager = Transaction.getInstance();
    }

    @Override
    public void addInspector(Inspector inspector) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("addInspector");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, inspector.getId());
            statement.setInt(2, inspector.getWorkNumber());
            statement.setInt(3, inspector.getWorkStatus());
            statement.setInt(4, inspector.getUserId());
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
    public Inspector getInspectorById(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getInspectorById");
        Inspector inspector = null;
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int workNumber = resultSet.getInt("work_number");
                int workStatus = resultSet.getInt("work_status");
                int userId = resultSet.getInt("user_id");

                inspector = new Inspector.Builder()
                        .setId(id)
                        .setWorkNumber(workNumber)
                        .setWorkStatus(workStatus)
                        .setUserId(userId)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inspector;
    }

    @Override
    public Inspector getRandomInspector() {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getRandomInspector");
        Inspector inspector = null;
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int workNumber = resultSet.getInt("work_number");
                int workStatus = resultSet.getInt("work_status");
                int userId = resultSet.getInt("user_id");

                inspector = new Inspector.Builder()
                        .setId(id)
                        .setWorkNumber(workNumber)
                        .setWorkStatus(workStatus)
                        .setUserId(userId)
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
        return inspector;
    }

    @Override
    public void updateInspector(Inspector inspector) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQueries.getProperty("updateInspector");
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, inspector.getId());
            statement.setInt(2, inspector.getWorkNumber());
            statement.setInt(3, inspector.getWorkStatus());
            statement.setInt(4, inspector.getUserId());
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
    public void deleteInspector(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQueries.getProperty("deleteInspector");
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
