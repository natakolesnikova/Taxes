package ua.tax.dao;

import ua.tax.dao.jdbc.InspectorDAOImpl;
import ua.tax.dao.jdbc.ReportDAOImpl;
import ua.tax.dao.jdbc.TaxPayerDAOImpl;
import ua.tax.dao.jdbc.UserDAOImpl;

public interface FactoryDAO {
    UserDAOImpl getUserDAO();
    TaxPayerDAOImpl getTaxPayerDAO();
    InspectorDAOImpl getInspectorDAO();
    ReportDAOImpl getReportDAO();
}
