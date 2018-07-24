package ua.tax.dao.jdbc;

import ua.tax.connection.ConnectionWrapper;
import ua.tax.connection.Transaction;
import ua.tax.dao.Mapper;
import ua.tax.dao.ReportDao;
import ua.tax.entity.Report;
import ua.tax.entity.StatusReport;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReportDAOImpl implements ReportDao {
    private Properties sqlQueries;
    private Transaction transactionManager;

    public ReportDAOImpl() {
        sqlQueries = Mapper.getProperties("sqlQueries");
        transactionManager = Transaction.getInstance();
    }

    @Override
    public void addReport(Report report) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("addReport");
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, report.getId());
            statement.setInt(2, report.getQuarterPeriod());
            statement.setBigDecimal(3, report.getIncome());
            statement.setBigDecimal(4, report.getTaxFromIncome());
            statement.setString(5, report.getStatusReport().toString());
            statement.setString(6, report.getRejectedReason());
            statement.setInt(7, report.getInspectorId());
            statement.setInt(8, report.getTaxpayerId());
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
    public List<Report> getReportByInspectorId(int inspectorId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getReportByInspectorId");
        List<Report> allReportsByInspectorId = new ArrayList<>();
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(7, inspectorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String status = resultSet.getString("status");
                String rejectedReason = resultSet.getString("rejected_reason");
                int taxPayerId = resultSet.getInt("taxpayer_id");

                Report report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(StatusReport.valueOf(status))
                        .setRejectedReason(rejectedReason)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxPayerId)
                        .build();
                allReportsByInspectorId.add(report);
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
        return allReportsByInspectorId;
    }

    @Override
    public List<Report> getReportByTaxPayerId(int taxpayerId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getReportByTaxPayerId");
        List<Report> allReportsByTaxPayerId = new ArrayList<>();
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(8, taxpayerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String status = resultSet.getString("status");
                String rejectedReason = resultSet.getString("rejected_reason");
                int inspectorId = resultSet.getInt(taxpayerId);

                Report report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(StatusReport.valueOf(status))
                        .setRejectedReason(rejectedReason)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxpayerId)
                        .build();
                allReportsByTaxPayerId.add(report);
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
        return allReportsByTaxPayerId;
    }

    @Override
    public List<Report> getReportByStatusAndInspectorId(StatusReport statusReport, int inspectorId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getReportByStatusAndInspectorId");
        List<Report> allReportsByStatusAndInspectorId = new ArrayList<>();
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setString(5, statusReport.toString());
            statement.setInt(7, inspectorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String rejectedReason = resultSet.getString("rejected_reason");
                int taxpayerId = resultSet.getInt("taxpayer_id");

                Report report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(statusReport)
                        .setRejectedReason(rejectedReason)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxpayerId)
                        .build();
                allReportsByStatusAndInspectorId.add(report);
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
        return allReportsByStatusAndInspectorId;
    }

    @Override
    public List<Report> getReportByStatusAndTaxPayerId(StatusReport statusReport, int taxPayer) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getReportByStatusAndTaxPayerId");
        List<Report> allReportsByStatusAndTaxPayerId = new ArrayList<>();

        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setString(5, statusReport.toString());
            statement.setInt(8, taxPayer);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String rejectedReason = resultSet.getString("rejected_reason");
                int inspectorId = resultSet.getInt("inspector_id");

                Report report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(statusReport)
                        .setRejectedReason(rejectedReason)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxPayer)
                        .build();
                allReportsByStatusAndTaxPayerId.add(report);
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
        return allReportsByStatusAndTaxPayerId;
    }

    @Override
    public List<Report> getReportsByInspectorAndTaxPayerId(int inspectorId, int taxPayerId) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        String sql = sqlQueries.getProperty("getReportsByInspectorAndTaxPayerId");
        List<Report> allReportsByInspectorAndPayerId = new ArrayList<>();
        try {
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(7, inspectorId);
            statement.setInt(8, taxPayerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String status = resultSet.getString("status");
                String rejectedReason = resultSet.getString("rejected_reason");

                Report report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(StatusReport.valueOf(status))
                        .setRejectedReason(rejectedReason)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxPayerId)
                        .build();
                allReportsByInspectorAndPayerId.add(report);
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
        return allReportsByInspectorAndPayerId;
    }

    @Override
    public void updateReport(Report report) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQueries.getProperty("updateReport");
            PreparedStatement statement = connectionWrapper.preparedStatement(sql);
            statement.setInt(1, report.getId());
            statement.setInt(2, report.getQuarterPeriod());
            statement.setBigDecimal(3, report.getIncome());
            statement.setBigDecimal(4, report.getTaxFromIncome());
            statement.setString(5, report.getStatusReport().toString());
            statement.setString(6, report.getRejectedReason());
            statement.setInt(7, report.getInspectorId());
            statement.setInt(8, report.getTaxpayerId());
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
    public void removeReport(int id) {
        ConnectionWrapper connectionWrapper = transactionManager.getConnection();
        try {
            String sql = sqlQueries.getProperty("removeReport");
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
