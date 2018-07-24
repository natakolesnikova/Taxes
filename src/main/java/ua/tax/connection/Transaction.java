package ua.tax.connection;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Transaction {
    private ThreadLocal<ConnectionWrapper> connections;
    private static Transaction instance = new Transaction();

    private Transaction() {
        connections = new ThreadLocal<>();
    }

    public static Transaction getInstance() {
        return instance;
    }

    public void beginTransaction() {
        ConnectionWrapper con = connections.get();
        if (Objects.nonNull(con)) {
            System.out.println("here shoul be exception");
        }
        try {

            Connection connection = ConnectionPool.getConnectionPool().getConnetcion();
            connection.setAutoCommit(false);
            con = new ConnectionWrapper(connection, true);
            connections.set(con);
        } catch (NamingException e) {
            System.out.println("here shoul be exception");
        } catch (SQLException e) {
            System.out.println("here shoul be exception");
        }
    }

    public void endTransaction() {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            System.out.println("here shoul be exception");
        }

        try {
            con.getConnection().close();
        } catch (SQLException e) {
            System.out.println("here shoul be exception");
        }
        connections.set(null);

    }

    public void rollback() {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            System.out.println("here shoul be exception");
        }

        try {
            con.getConnection().rollback();
        } catch (SQLException e) {
            System.out.println("here shoul be exception");
        }
    }

    public void commit() {
        ConnectionWrapper con = connections.get();
        if (Objects.isNull(con)) {
            System.out.println("here shoul be exception");
        }

        try {
            con.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("here shoul be exception");
        }
    }


    public ConnectionWrapper getConnection()  {
        ConnectionWrapper con = connections.get();
        if (Objects.nonNull(con)) {
            return con;
        }
        try {
            Connection connection = ConnectionPool.getConnectionPool().getConnetcion();
            connection.setAutoCommit(true);
            con = new ConnectionWrapper(connection, false);
            return con;
        }catch(NamingException e){
            System.out.println("here shoul be exception");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }

}
