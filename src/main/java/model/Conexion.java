package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Clase de la conexion a la base de datos
public class Conexion {
    Connection connection = null;
    String base = "libreria";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "1234";

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);

            //Crea la base de datos de primeras si no existe y luego la usa
            connection.createStatement().execute("CREATE DATABASE IF NOT EXISTS " + base);

            connection.createStatement().execute("USE "+ base);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void cerrarConexion() throws SQLException {
        connection.close();
    }
}
