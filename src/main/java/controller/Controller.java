package controller;

import model.Autor;
import model.AutorService;
import model.Conexion;
import model.DatabaseService;
import view.Inicio;
import view.VistaAutor;
import view.VistaLibro;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;

public class Controller {
    private Inicio ventanaInicio;
    private VistaAutor ventanaAutor;
    private VistaLibro ventanaLibro;
    private final DatabaseService databaseService;
    private final AutorService autorService;
    private final Conexion conexion;


    public Controller(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        conexion = new Conexion();
        conexion.getConnection();

        databaseService = new DatabaseService(conexion);
        databaseService.crearTablaAutores();
        databaseService.crearTablaLibros();

        this.autorService = new AutorService(conexion);


        this.ventanaInicio.btnAutor.addActionListener(e -> abrirVentanaAutor());
        this.ventanaInicio.btnLibro.addActionListener(e -> abrirVentanaLibro());
        this.ventanaInicio.btnSalir.addActionListener(e -> System.exit(0));

    }

    public void abrirVentanaAutor() {
        this.ventanaAutor = new VistaAutor();
        this.ventanaAutor.btnAgregar.addActionListener(e -> crearAutor());
        this.ventanaAutor.btnEliminar.addActionListener(e -> borrarAutor());
        this.ventanaAutor.btnActualizar.addActionListener(e -> actualizarAutor());
        this.ventanaAutor.btnSalir.addActionListener(e -> System.exit(0));
        actualizarListaVista();
    }

    public void abrirVentanaLibro() {
        this.ventanaLibro = new VistaLibro();
    }

    public void actualizarListaVista() {
        ventanaAutor.modeloLlista.clear();
        for (Autor autor : autorService.getAutores()) {
            ventanaAutor.modeloLlista.addElement(autor);
        }
    }

    public void crearAutor() {
        String nombre = ventanaAutor.txNombre.getText();
        String apellidos = ventanaAutor.txApelidos.getText();
        String nacionalidad = ventanaAutor.txNacionalidad.getText();
        Date fechaNacimiento;
        try {
            fechaNacimiento = Date.valueOf(ventanaAutor.txFechaNacimiento.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(ventanaAutor, "Formato de la fecha incorrecto (AAAA-MM-DD)");
            return;
        }
        if (nombre.isEmpty() || apellidos.isEmpty() || nacionalidad.isEmpty()) {
            JOptionPane.showMessageDialog(ventanaAutor, "Necesitas introducir todos los campos para añadir un autor");
            return;
        }
        autorService.crearAutor(nombre, apellidos, nacionalidad, fechaNacimiento);
        actualizarListaVista();
    }

    public void borrarAutor() {
        int id;
        try {
            id = ventanaAutor.listaAutores.getSelectedValue().getId();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(ventanaAutor, "Selecciona un autor para poder eliminarlo");
            return;
        }
        autorService.borrarAutor(id);
        actualizarListaVista();
    }

    public void actualizarAutor() {
        int id = 0;
        try {
            id = ventanaAutor.listaAutores.getSelectedValue().getId();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(ventanaAutor, "Selecciona un autor para poder actualizarlo");
            return;
        }

        if (!ventanaAutor.txNombre.getText().isEmpty()) {
            String nombre = ventanaAutor.txNombre.getText();
            autorService.actualizarAutorString(id, "nombre", nombre);
        }
        if (!ventanaAutor.txApelidos.getText().isEmpty()) {
            String apellidos = ventanaAutor.txApelidos.getText();
            autorService.actualizarAutorString(id, "apellidos", apellidos);
        }
        if (!ventanaAutor.txNacionalidad.getText().isEmpty()) {
            String nacionalidad = ventanaAutor.txNacionalidad.getText();
            autorService.actualizarAutorString(id, "nacionalidad", nacionalidad);
        }
        if (!ventanaAutor.txFechaNacimiento.getText().isEmpty()) {
            Date fechaNacimiento;
            try {
                fechaNacimiento = Date.valueOf(ventanaAutor.txFechaNacimiento.getText());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(ventanaAutor, "Formato de la fecha incorrecto (AAAA-MM-DD)");
                return;
            }
            autorService.actualizarAutorDate(id, "fecha_nacimiento", fechaNacimiento);
        }
        actualizarListaVista();
    }
}
