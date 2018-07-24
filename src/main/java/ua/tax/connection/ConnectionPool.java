package ua.tax.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private final DataSource dataSource;

    private ConnectionPool() throws NamingException {
        Context initialContext = new InitialContext();
        Context environmentContext = (Context) initialContext.lookup("java:comp/env");
        dataSource = (DataSource) environmentContext.lookup("jdbc:tax");
    }

    public static synchronized ConnectionPool getConnectionPool() throws NamingException {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnetcion() throws SQLException {
        return dataSource.getConnection();
    }
}
