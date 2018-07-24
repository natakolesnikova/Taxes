package ua.tax.dao;

import ua.tax.entity.Report;
import ua.tax.entity.StatusReport;

import java.util.List;

public interface ReportDao {
    //create report
    void addReport(Report report);

    //read
    List<Report> getReportByInspectorId(int inspectorId);
    List<Report> getReportByTaxPayerId(int taxpayerId);
    List<Report> getReportByStatusAndInspectorId(StatusReport statusReport, int inspectorId);
    List<Report> getReportByStatusAndTaxPayerId(StatusReport statusReport, int taxPayer);
    List<Report> getReportsByInspectorAndTaxPayerId(int inspectorId, int taxPayerId);

    //update
    void updateReport(Report report);

    //delete
    void removeReport(int id);

}
