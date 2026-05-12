package controller;

import model.Autor;
import model.AutorService;
import view.VistaAutor;

import javax.swing.*;
import java.sql.Date;

public class AutorController {
    private final AutorService autorService;
    private VistaAutor ventanaAutor;
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
        this.ventanaAutor = new VistaAutor();
        this.ventanaAutor.btnAgregar.addActionListener(e -> crearAutor());
        this.ventanaAutor.btnEliminar.addActionListener(e -> borrarAutor());
        this.ventanaAutor.btnActualizar.addActionListener(e -> actualizarAutor());
        this.ventanaAutor.btnSalir.addActionListener(e -> ventanaAutor.dispose());
        actualizarListaVistaAutor();
    }

    public void actualizarListaVistaAutor() {
        ventanaAutor.modeloLlista.clear();
        for (Autor autor : autorService.getAutores()) {
            ventanaAutor.modeloLlista.addElement(autor);
        }
    }

    public void crearAutor() {
        String mensajeError = "";
        String nombre = ventanaAutor.txNombre.getText();
        String apellidos = ventanaAutor.txApelidos.getText();
        String nacionalidad = ventanaAutor.txNacionalidad.getText();
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = Date.valueOf(ventanaAutor.txFechaNacimiento.getText());
        } catch (IllegalArgumentException e) {
            mensajeError = mensajeError.concat("Formato de la fecha incorrecto (AAAA-MM-DD)\n");
        }
        if (nombre.isEmpty() || apellidos.isEmpty() || nacionalidad.isEmpty()) {
            mensajeError = mensajeError.concat("Necesitas introducir todos los campos para añadir un autor");
        }

        if (mensajeError.isEmpty()) {
            autorService.crearAutor(nombre, apellidos, nacionalidad, fechaNacimiento);
            actualizarListaVistaAutor();
        } else {
            JOptionPane.showMessageDialog(ventanaAutor, mensajeError);
        }
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
        actualizarListaVistaAutor();
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
        actualizarListaVistaAutor();
    }
}
