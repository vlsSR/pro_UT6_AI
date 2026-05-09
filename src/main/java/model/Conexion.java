package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection connection = null;
    String base = "libreria";
    String url = "jdbc:mysql://localhost:3306/"+base;
    String user = "root";
    String password = "1234";

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void cerrarConexion() throws SQLException {
        connection.close();
    }
}
