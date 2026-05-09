package controller;

import model.Conexion;
import model.DatabaseService;
import view.Inicio;
import view.VistaAutor;
import view.VistaLibro;

public class Controller {
    private VistaAutor ventanaAutor;
    private VistaLibro ventanaLibro;
    private final DatabaseService databaseService;
    private final Conexion conexion;

    public Controller(Inicio vista) {
        conexion = new Conexion();
        conexion.getConnection();
        databaseService = new DatabaseService(conexion);
        databaseService.crearTablaAutores();
        databaseService.crearTablaLibros();
        vista.btnAutor.addActionListener(e -> abrirVentanaAutor());
        vista.btnLibro.addActionListener(e -> abrirVentanaLibro());
        vista.btnSalir.addActionListener(e -> System.exit(0));
    }

    public void abrirVentanaAutor() {
        this.ventanaAutor = new VistaAutor();
    }

    public void abrirVentanaLibro() {
        this.ventanaLibro = new VistaLibro();
    }
}
