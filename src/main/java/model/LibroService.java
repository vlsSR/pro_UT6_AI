package model;

import java.sql.*;
import java.util.ArrayList;

//Clase encargada de las operaciones SQL relacionadas con autores
public class LibroService {
    private final Conexion conexion;
    private ArrayList<Libro> libros;

    public LibroService(Conexion conexion) {
        this.conexion = conexion;
        libros = new ArrayList<>();
    }

    public void crearLibro(String isbn, String titulo, String genero, int stock, Date fechaPublicacion, int id_autor) {
        String query = "INSERT INTO libros(isbn, titulo, genero, stock, fecha_publicacion, id_autor) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement psi = conexion.connection.prepareStatement(query);
            psi.setString(1, isbn);
            psi.setString(2, titulo);
            psi.setString(3, genero);
            psi.setInt(4, stock);
            psi.setDate(5, fechaPublicacion);
            psi.setInt(6, id_autor);
            psi.executeUpdate();
            psi.close();
            actualizarLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibros() {
        libros.clear();
        String query = "SELECT * FROM libros";
        try {
            Statement statement = conexion.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                libros.add(new Libro(rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("stock"),
                        rs.getDate("fecha_publicacion"),
                        rs.getInt("id_autor")
                ));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarLibro(int id) {
        String query = "DELETE FROM libros WHERE id=?";
        try {
            PreparedStatement psi = conexion.connection.prepareStatement(query);
            psi.setInt(1, id);
            psi.executeUpdate();
            psi.close();
            actualizarLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibroString(int id, String campo, String nuevoValor) {
        String query = "UPDATE libros SET "+campo+"=? WHERE id=?";
        try {
            PreparedStatement psi=conexion.connection.prepareStatement(query);
            psi.setString(1, nuevoValor);
            psi.setInt(2, id);
            psi.executeUpdate();
            psi.close();
            actualizarLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibroDate(int id, String campo, Date nuevoValor) {
        String query = "UPDATE libros SET "+campo+"=? WHERE id=?";
        try {
            PreparedStatement psi=conexion.connection.prepareStatement(query);
            psi.setDate(1, nuevoValor);
            psi.setInt(2, id);
            psi.executeUpdate();
            psi.close();
            actualizarLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarLibroInt(int id, String campo, int nuevoValor) {
        String query = "UPDATE libros SET "+campo+"=? WHERE id=?";
        try {
            PreparedStatement psi=conexion.connection.prepareStatement(query);
            psi.setInt(1, nuevoValor);
            psi.setInt(2, id);
            psi.executeUpdate();
            psi.close();
            actualizarLibros();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Libro> getLibros() {
        actualizarLibros();
        return libros;
    }
}
