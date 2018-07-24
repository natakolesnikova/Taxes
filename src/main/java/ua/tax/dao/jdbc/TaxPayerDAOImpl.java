package ua.tax.dao.jdbc;

import ua.tax.connection.ConnectionWrapper;
import ua.tax.connection.Transaction;
import ua.tax.dao.Mapper;
import ua.tax.dao.TaxPayerDAO;
import ua.tax.entity.TaxPayer;
import ua.tax.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaxPayerDAOImpl implements TaxPayerDAO {
    private Properties sqlQueries;
    private Transaction transactionManager;

    public TaxPayerDAOImpl() {
        sqlQueries = Mapper.getProperties("sqlQueries");
        transactionManager = Transaction.getInstance();
    }

    @Override
    public void addTaxpayer(TaxPayer taxpeyer) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("addTaxpayer");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, taxpeyer.getId());
            statement.setString(2, taxpeyer.getPasswordSerialNumber());
            statement.setInt(3, taxpeyer.getPassportNumber());
            statement.setInt(4, taxpeyer.getIdentificationCode());
            statement.setInt(5, taxpeyer.getUserId());
            statement.setInt(6, taxpeyer.getInspectorId());
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
    public List<TaxPayer> getAllTaxPayer() {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        List<TaxPayer> allTaxPayers = new ArrayList<>();
        String sql = sqlQueries.getProperty("getAllTaxPayer");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String passportSerialNumber = resultSet.getString("passport_serial_number");
                int passportNumber = resultSet.getInt("passport_number");
                int identificationCode = resultSet.getInt("identification_code");
                int userId = resultSet.getInt("user_id");
                int inspectorId = resultSet.getInt("inspector_id");

                TaxPayer taxPayer = new TaxPayer.Builder()
                        .setId(id)
                        .setPasswordSerialNumber(passportSerialNumber)
                        .setPassportNumber(passportNumber)
                        .setIdentificationCode(identificationCode)
                        .setUserId(userId)
                        .setInspectorId(inspectorId)
                        .build();

                allTaxPayers.add(taxPayer);
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
        return allTaxPayers;
    }

    @Override
    public List<TaxPayer> getTaxPayersByInspectorId(int inspectorId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getTaxPayersByInspectorId");
        List<TaxPayer> allTaxpayerByInspectorId = new ArrayList<>();
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(6, inspectorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String passportSerialNumber = resultSet.getString("passport_serial_number");
                int passportNumber = resultSet.getInt("passport_number");
                int identificationCode = resultSet.getInt("identification_code");
                int userId = resultSet.getInt("user_id");

                TaxPayer taxPayer = new TaxPayer.Builder()
                        .setId(id)
                        .setPasswordSerialNumber(passportSerialNumber)
                        .setPassportNumber(passportNumber)
                        .setIdentificationCode(identificationCode)
                        .setUserId(userId)
                        .setInspectorId(inspectorId)
                        .build();

                allTaxpayerByInspectorId.add(taxPayer);
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
        return allTaxpayerByInspectorId;
    }

    @Override
    public TaxPayer getTaxPayerByUserId(int userId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getTaxPayerByUserId");
        TaxPayer taxPayer = null;
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(5, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String passportSerialNumber = resultSet.getString("passport_serial_number");
                int passportNumber = resultSet.getInt("passport_number");
                int identificationCode = resultSet.getInt("identification_code");
                int inspectorId = resultSet.getInt("inspector_id");

                taxPayer = new TaxPayer.Builder()
                        .setUserId(id)
                        .setPasswordSerialNumber(passportSerialNumber)
                        .setPassportNumber(passportNumber)
                        .setIdentificationCode(identificationCode)
                        .setUserId(userId)
                        .setInspectorId(inspectorId)
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
        return taxPayer;
    }

    @Override
    public TaxPayer getTaxPayerById(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getTaxPayerById");
        TaxPayer taxPayer = null;
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String passportSerialNumber = resultSet.getString("passport_serial_number");
                int passportNumber = resultSet.getInt("passport_number");
                int identificationCode = resultSet.getInt("identification_code");
                int userId = resultSet.getInt("user_id");
                int inspectorId = resultSet.getInt("inspector_id");

                taxPayer = new TaxPayer.Builder()
                        .setId(id)
                        .setPasswordSerialNumber(passportSerialNumber)
                        .setPassportNumber(passportNumber)
                        .setIdentificationCode(identificationCode)
                        .setUserId(userId)
                        .setInspectorId(inspectorId)
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
        return taxPayer;
    }

    @Override
    public void updateTaxPayer(TaxPayer taxPayer) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("updateTaxPayer");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, taxPayer.getId());
            statement.setString(2, taxPayer.getPasswordSerialNumber());
            statement.setInt(3, taxPayer.getPassportNumber());
            statement.setInt(4, taxPayer.getIdentificationCode());
            statement.setInt(5, taxPayer.getUserId());
            statement.setInt(6, taxPayer.getInspectorId());
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
    public void deleteTaxPayer(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQueries.getProperty("deleteTaxPayer");
            PreparedStatement preparedStatement = connectionWrapper.preparedStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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
