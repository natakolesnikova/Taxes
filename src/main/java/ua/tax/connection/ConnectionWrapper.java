package ua.tax.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
    private Connection connection;
    private boolean isTransaction;

    public ConnectionWrapper(Connection connection, boolean isTransaction) {
        this.connection = connection;
        this.isTransaction = isTransaction;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public PreparedStatement preparedStatement(String sql, int keys) throws SQLException {
        return connection.prepareStatement(sql, keys);
    }

    public void close() throws SQLException {
        if (!isTransaction) {
            connection.close();
        }
    }
}
