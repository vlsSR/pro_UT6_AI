package controller;

import model.*;
import view.Inicio;

import javax.swing.*;
import java.sql.SQLException;

//Controlador principal del programa, relacionad con la ventana de Inicio
public class Controller {
    private Inicio ventanaInicio;
    private final DatabaseService databaseService;
    private final AutorService autorService;
    private LibroService libroService;
    private final Conexion conexion;


    //Constructor que crea las tablas e instancia los servicios ademas de añadir los actions listeners
    public Controller(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        conexion = new Conexion();
        conexion.getConnection();

        databaseService = new DatabaseService(conexion);
        databaseService.crearTablaAutores();
        databaseService.crearTablaLibros();

        this.autorService = new AutorService(conexion);
        this.libroService = new LibroService(conexion);

        this.ventanaInicio.btnAutor.addActionListener(e -> abrirVentanaAutor());
        this.ventanaInicio.btnLibro.addActionListener(e -> abrirVentanaLibro());
        this.ventanaInicio.btnSalir.addActionListener(e -> salir());

    }

    public void abrirVentanaAutor() {
        new AutorController(autorService);
    }

    public void abrirVentanaLibro() {
        new LibroController(libroService, autorService);
    }

    //Primero cierra la conexion con la base datos y luego cierra el programa
    public void salir() {
        try {
            conexion.cerrarConexion();
            System.exit(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
