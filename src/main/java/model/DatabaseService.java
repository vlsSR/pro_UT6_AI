package model;

import java.sql.SQLException;

public class DatabaseService {
    private final Conexion conexion;

    public DatabaseService(Conexion conexion) {
        this.conexion = conexion;
    }

    public void crearTablaAutores() {
        String query = "CREATE TABLE IF NOT EXISTS autores(" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(30), " +
                "apellidos VARCHAR(80), " +
                "nacionalidad VARCHAR(20), " +
                "fecha_nacimiento DATE " +
                ")";

        try {
            conexion.connection.createStatement().execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearTablaLibros() {
        String query = "CREATE TABLE IF NOT EXISTS libros(" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "isbn VARCHAR(13), " +
                "titulo VARCHAR(100), " +
                "genero VARCHAR(100), " +
                "stock INT, " +
                "fecha_publicacion DATE, " +
                "id_autor INT, " +
                "FOREIGN KEY (id_autor) REFERENCES autores(id)" +
                ")";

        try {
            conexion.connection.createStatement().execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
