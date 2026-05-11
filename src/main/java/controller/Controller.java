package controller;

import model.*;
import view.Inicio;
import view.VistaAutor;
import view.VistaLibro;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;

public class Controller {
    private Inicio ventanaInicio;
    private VistaAutor ventanaAutor;
    private VistaLibro ventanaLibro;
    private final DatabaseService databaseService;
    private final AutorService autorService;
    private final LibroService libroService;
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

    public void abrirVentanaLibro() {
        this.ventanaLibro = new VistaLibro();
        this.ventanaLibro.btnAgregar.addActionListener(e -> crearLibro());
        this.ventanaLibro.btnEliminar.addActionListener(e -> borrarLibro());
        this.ventanaLibro.btnActualizar.addActionListener(e -> actualizarLibro());
        this.ventanaLibro.btnSalir.addActionListener(e -> ventanaLibro.dispose());
        actualizarListaVistaLibro();
    }

    public void actualizarListaVistaLibro() {
        ventanaLibro.modeloLlista.clear();
        for (Libro libro : libroService.getLibros()) {
            ventanaLibro.modeloLlista.addElement(libro);
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

    public void crearLibro() {
        String mensajeError = "";
        String isbn = ventanaLibro.txIsbn.getText();
        String titulo = ventanaLibro.txTitulo.getText();
        String genero = ventanaLibro.txGenero.getText();
        int stock = 0;
        int idAutor = 0;

        try {
             stock = Integer.parseInt(ventanaLibro.txStock.getText());
        } catch (NumberFormatException ex) {
            mensajeError = mensajeError.concat("Formato del stock incorrecto (solo numeros positivos)\n");
        }

        try {
            idAutor = Integer.parseInt(ventanaLibro.txIdAutor.getText());
        } catch (NumberFormatException ex) {
            mensajeError = mensajeError.concat("Formato del id del autor incorrecto (solo numeros positivos)\n");
        }

        Date fechaPublicacion = null;

        try {
            fechaPublicacion = Date.valueOf(ventanaLibro.txFechaPublicacion.getText());
        } catch (IllegalArgumentException e) {
            mensajeError = mensajeError.concat("Formato de la fecha incorrecto (AAAA-MM-DD)\n");
        }

        if (!existeAutor(idAutor)) {
            mensajeError = mensajeError.concat("No existe un autor con ese id\n");
        }

        if (isbn.isEmpty() || titulo.isEmpty() || genero.isEmpty()) {
            mensajeError = mensajeError.concat("Necesitas introducir todos los campos para añadir un libro");
        }

        if (mensajeError.isEmpty()) {
            libroService.crearLibro(isbn, titulo, genero, stock, fechaPublicacion, idAutor);
            actualizarListaVistaLibro();
        } else {
            JOptionPane.showMessageDialog(ventanaLibro, mensajeError);
        }

    }

    public void borrarLibro() {
        int id;
        try {
            id = ventanaLibro.listaLibros.getSelectedValue().getId();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(ventanaLibro, "Selecciona un libro para poder eliminarlo");
            return;
        }
        libroService.borrarLibro(id);
        actualizarListaVistaLibro();
    }

    public void actualizarLibro() {
        int id = 0;
        try {
            id = ventanaLibro.listaLibros.getSelectedValue().getId();
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(ventanaLibro, "Selecciona un autor para poder actualizarlo");
            return;
        }

        if (!ventanaLibro.txIsbn.getText().isEmpty()) {
            String isbn = ventanaLibro.txIsbn.getText();
            libroService.actualizarLibroString(id, "isbn", isbn);
        }
        if (!ventanaLibro.txTitulo.getText().isEmpty()) {
            String titulo = ventanaLibro.txTitulo.getText();
            libroService.actualizarLibroString(id, "titulo", titulo);
        }
        if (!ventanaLibro.txGenero.getText().isEmpty()) {
            String genero = ventanaLibro.txGenero.getText();
            libroService.actualizarLibroString(id, "genero", genero);
        }
        if (!ventanaLibro.txStock.getText().isEmpty()) {
            int stock;
            try {
                stock = Integer.parseInt(ventanaLibro.txStock.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventanaLibro, "Formato incorrecto del stock");
                return;
            }

            libroService.actualizarLibroInt(id, "stock", stock);
        }
        if (!ventanaLibro.txFechaPublicacion.getText().isEmpty()) {
            Date fechaPublicacion;
            try {
                fechaPublicacion = Date.valueOf(ventanaLibro.txFechaPublicacion.getText());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(ventanaAutor, "Formato de la fecha incorrecto (AAAA-MM-DD)");
                return;
            }
            libroService.actualizarLibroDate(id, "fecha_publicacion", fechaPublicacion);
        }
        if (!ventanaLibro.txIdAutor.getText().isEmpty()) {
            int id_autor;
            try {
                id_autor = Integer.parseInt(ventanaLibro.txIdAutor.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventanaLibro, "Formato incorrecto del id_autor");
                return;
            }

            if (existeAutor(id_autor)) {
                libroService.actualizarLibroInt(id, "id_autor", id_autor);
            } else {
                JOptionPane.showMessageDialog(ventanaLibro, "No existe un autor con ese id");
                return;
            }

        }
        actualizarListaVistaLibro();
    }

    public boolean existeAutor(int id) {
        for (Autor autor : autorService.getAutores()) {
            if (autor.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
