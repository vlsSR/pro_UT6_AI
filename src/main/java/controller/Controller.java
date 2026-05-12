package controller;

import model.*;
import view.Inicio;

import javax.swing.*;

public class Controller {
    private Inicio ventanaInicio;
    private final DatabaseService databaseService;
    private static AutorService autorService;
    private LibroService libroService;
    private final Conexion conexion;


    public Controller(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        conexion = new Conexion();
        conexion.getConnection();

        databaseService = new DatabaseService(conexion);
        databaseService.crearTablaAutores();
        databaseService.crearTablaLibros();

        this.libroService = new LibroService(conexion);

        this.ventanaInicio.btnAutor.addActionListener(e -> abrirVentanaAutor());
        this.ventanaInicio.btnLibro.addActionListener(e -> abrirVentanaLibro());
        this.ventanaInicio.btnSalir.addActionListener(e -> System.exit(0));

    }

    public void abrirVentanaAutor() {
        autorService = new AutorService(conexion);
        new AutorController(autorService);
    }

    public void abrirVentanaLibro() {
        this.libroService = new LibroService(conexion);
        new LibroController(libroService, autorService);
    }
}
