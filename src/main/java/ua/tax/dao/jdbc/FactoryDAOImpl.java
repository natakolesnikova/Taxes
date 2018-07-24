package ua.tax.dao.jdbc;

import ua.tax.dao.*;

public class FactoryDAOImpl implements FactoryDAO {
    private static FactoryDAOImpl factory;

    private InspectorDAOImpl inspectorDAO;
    private ReportDAOImpl reportDAO;
    private TaxPayerDAOImpl taxPayerDAO;
    private UserDAOImpl userDAO;

    public FactoryDAOImpl() {
        inspectorDAO = new InspectorDAOImpl();
        reportDAO = new ReportDAOImpl();
        taxPayerDAO = new TaxPayerDAOImpl();
        userDAO = new UserDAOImpl();
    }

    public static synchronized FactoryDAOImpl getInstance() {
        if (factory == null) {
            factory = new FactoryDAOImpl();
        }
        return factory;
    }

    @Override
    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    @Override
    public TaxPayerDAOImpl getTaxPayerDAO() {
        return taxPayerDAO;
    }

    @Override
    public InspectorDAOImpl getInspectorDAO() {
        return inspectorDAO;
    }

    @Override
    public ReportDAOImpl getReportDAO() {
        return reportDAO;
    }
}
