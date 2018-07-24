package ua.tax.entity;

public class TaxPayer extends User{
    private int id;
    private String passwordSerialNumber;
    private int passportNumber;
    private int identificationCode;
    private int userId;
    private int inspectorId;

    public static class Builder {
        private int id;
        private String passwordSerialNumber;
        private int passportNumber;
        private int identificationCode;
        private int userId;
        private int inspectorId;

        public Builder() {}

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPasswordSerialNumber(String passwordSerialNumber) {
            this.passwordSerialNumber = passwordSerialNumber;
            return this;
        }

        public Builder setPassportNumber(int passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public Builder setIdentificationCode(int identificationCode) {
            this.identificationCode = identificationCode;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setInspectorId(int inspectorId) {
            this.inspectorId = inspectorId;
            return this;
        }

        public TaxPayer build() {
            TaxPayer taxPayer = new TaxPayer();
            taxPayer.id = id;
            taxPayer.passwordSerialNumber = passwordSerialNumber;
            taxPayer.passportNumber = passportNumber;
            taxPayer.identificationCode = identificationCode;
            taxPayer.userId = userId;
            taxPayer.inspectorId = inspectorId;
            return taxPayer;
        }
    }


    public String getPasswordSerialNumber() {
        return passwordSerialNumber;
    }

    public void setPasswordSerialNumber(String passwordSerialNumber) {
        this.passwordSerialNumber = passwordSerialNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(int identificationCode) {
        this.identificationCode = identificationCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(int inspectorId) {
        this.inspectorId = inspectorId;
    }
}
