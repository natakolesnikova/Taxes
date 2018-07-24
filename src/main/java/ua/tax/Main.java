package ua.tax;

import ua.tax.util.ConnectorDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectorDB connectorDB = new ConnectorDB();
        try {
            connectorDB.getConnection();
            System.out.println("ok");
        } catch (SQLException e) {
            System.out.println("not ok");
            e.printStackTrace();
        }
    }
}
