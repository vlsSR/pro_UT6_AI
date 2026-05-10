package model;

import java.sql.*;
import java.util.ArrayList;

public class AutorService {
    private final Conexion conexion;
    private ArrayList<Autor> autores;

    public AutorService(Conexion conexion) {
        this.conexion = conexion;
        autores = new ArrayList<>();
    }

    public void crearAutor(String nombre, String apellidos, String nacionalidad, Date fechaNacimiento) {
        String query = "INSERT INTO autores(nombre, apellidos, nacionalidad, fecha_nacimiento) VALUES (?,?,?,?)";
        try {
            PreparedStatement psi = conexion.connection.prepareStatement(query);
            psi.setString(1, nombre);
            psi.setString(2, apellidos);
            psi.setString(3, nacionalidad);
            psi.setDate(4, fechaNacimiento);
            psi.executeUpdate();
            psi.close();
            actualizarAutores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarAutores() {
        autores.clear();
        String query = "SELECT * FROM autores";
        try {
            Statement statement = conexion.connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                autores.add(new Autor(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("nacionalidad"),
                        rs.getDate("fecha_nacimiento")
                        ));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarAutor(int id) {
        String query = "DELETE FROM autores WHERE id=?";
        try {
            PreparedStatement psi = conexion.connection.prepareStatement(query);
            psi.setInt(1, id);
            psi.executeUpdate();
            psi.close();
            actualizarAutores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarAutorString(int id, String campo, String nuevoValor) {
        String query = "UPDATE autores SET "+campo+"=? WHERE id=?";
        try {
            PreparedStatement psi=conexion.connection.prepareStatement(query);
            psi.setString(1, nuevoValor);
            psi.setInt(2, id);
            psi.executeUpdate();
            psi.close();
            actualizarAutores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarAutorDate(int id, String campo, Date nuevoValor) {
        String query = "UPDATE autores SET "+campo+"=? WHERE id=?";
        try {
            PreparedStatement psi=conexion.connection.prepareStatement(query);
            psi.setDate(1, nuevoValor);
            psi.setInt(2, id);
            psi.executeUpdate();
            psi.close();
            actualizarAutores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Autor> getAutores() {
        actualizarAutores();
        return autores;
    }
}
