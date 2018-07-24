package ua.tax.dao;

import ua.tax.entity.TaxPayer;

import java.util.List;

public interface TaxPayerDAO {

    //create
    void addTaxpayer(TaxPayer taxpeyer);

    //read
    List<TaxPayer> getAllTaxPayer();
    List<TaxPayer> getTaxPayersByInspectorId(int inspectorId);
    TaxPayer getTaxPayerByUserId(int userId);
    TaxPayer getTaxPayerById(int id);

    //update
    void updateTaxPayer(TaxPayer taxPayer);

    //delete
    void deleteTaxPayer(int id);
}
