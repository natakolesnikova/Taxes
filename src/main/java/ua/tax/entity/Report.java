package ua.tax.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Report implements Serializable, Cloneable {
    private int id;
    private int quarterPeriod;
    private BigDecimal income;
    private BigDecimal taxFromIncome;
    private StatusReport statusReport;
    private String rejectedReason;
    private int inspectorId;
    private int taxpayerId;

    public Report() {
    }

    public static class Builder {
        private int id;
        private int quarterPeriod;
        private BigDecimal income;
        private BigDecimal taxFromIncome;
        private StatusReport statusReport;
        private String rejectedReason;
        private int inspectorId;
        private int taxpayerId;

        public Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setQuarterPeriod(int quarterPeriod) {
            this.quarterPeriod = quarterPeriod;
            return this;
        }

        public Builder setIncome(BigDecimal income) {
            this.income = income;
            return this;
        }

        public Builder setTaxFromIncome(BigDecimal taxFromIncome) {
            this.taxFromIncome = taxFromIncome;
            return this;
        }

        public Builder setStatusReport(StatusReport statusReport) {
            this.statusReport = statusReport;
            return this;
        }

        public Builder setRejectedReason(String rejectedReason) {
            this.rejectedReason = rejectedReason;
            return this;
        }

        public Builder setInspectorId(int inspectorId) {
            this.inspectorId = inspectorId;
            return this;
        }

        public Builder setTaxpayerId(int taxpayerId) {
            this.taxpayerId = taxpayerId;
            return this;
        }

        public Report build() {
            Report report = new Report();
            report.id = id;
            report.quarterPeriod = quarterPeriod;
            report.income = income;
            report.taxFromIncome = taxFromIncome;
            report.statusReport = statusReport;
            report.rejectedReason = rejectedReason;
            report.inspectorId = inspectorId;
            report.taxpayerId = taxpayerId;
            return report;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuarterPeriod() {
        return quarterPeriod;
    }

    public void setQuarterPeriod(int quarterPeriod) {
        this.quarterPeriod = quarterPeriod;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getTaxFromIncome() {
        return taxFromIncome;
    }

    public void setTaxFromIncome(BigDecimal taxFromIncome) {
        this.taxFromIncome = taxFromIncome;
    }

    public StatusReport getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(StatusReport statusReport) {
        this.statusReport = statusReport;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }

    public int getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(int taxpayerId) {
        this.taxpayerId = taxpayerId;
    }
}

