package ua.tax.dao;

import ua.tax.entity.Inspector;

import java.util.List;

public interface InspectorDAO {

    //create
    void addInspector(Inspector inspector);

    //read
    Inspector getInspectorById(int id);
    Inspector getRandomInspector();

    //update
    void updateInspector(Inspector inspector);

    //delete
    void deleteInspector(int id);
}
