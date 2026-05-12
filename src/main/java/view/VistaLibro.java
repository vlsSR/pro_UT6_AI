package view;

import model.Libro;

import javax.swing.*;
import java.awt.*;

public class VistaLibro extends JFrame{
    public JTextField txIsbn = new JTextField(20);
    public JTextField txTitulo = new JTextField(20);
    public JTextField txGenero = new JTextField(20);
    public JTextField txStock = new JTextField(20);
    public JTextField txFechaPublicacion = new JTextField(20);
    public JTextField txIdAutor = new JTextField(20);
    public JButton btnAgregar = new JButton("Añadir");
    public DefaultListModel<Libro> modeloLlista = new DefaultListModel<>();
    public JList<Libro> listaLibros = new JList<>(modeloLlista);

    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnSalir = new JButton("Salir");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBuscar = new JButton("Buscar por id");


    public VistaLibro() {
        setTitle("Gestion libros");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelUP = new JPanel(new GridLayout(4, 3));
        panelUP.add(new JLabel("isbn:"));
        panelUP.add(new JLabel("Titulo:"));
        panelUP.add(new JLabel("Genero:"));
        panelUP.add(txIsbn);
        panelUP.add(txTitulo);
        panelUP.add(txGenero);

        panelUP.add(new JLabel("Stock:"));
        panelUP.add(new JLabel("Fecha de publicacion:"));
        panelUP.add(new JLabel("Id del autor:"));
        panelUP.add(txStock);
        panelUP.add(txFechaPublicacion);
        panelUP.add(txIdAutor);

        JScrollPane scrollPane = new JScrollPane(listaLibros);

        JPanel panelDOWN = new JPanel();
        panelDOWN.add(btnBuscar);
        panelDOWN.add(btnAgregar);
        panelDOWN.add(btnActualizar);
        panelDOWN.add(btnEliminar);
        panelDOWN.add(btnSalir);

        add(panelUP, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelDOWN, BorderLayout.SOUTH);

        setSize(800, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
