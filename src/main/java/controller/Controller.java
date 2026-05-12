package controller;

import model.*;
import view.Inicio;

import javax.swing.*;

public class Controller {
    private Inicio ventanaInicio;
    private final DatabaseService databaseService;
    private final AutorService autorService;
    private LibroService libroService;
    private final Conexion conexion;


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
        this.ventanaInicio.btnSalir.addActionListener(e -> System.exit(0));

    }

    public void abrirVentanaAutor() {
        new AutorController(autorService);
    }

    public void abrirVentanaLibro() {
        new LibroController(libroService, autorService);
    }
}
