package view;

import model.Autor;

import javax.swing.*;
import java.awt.*;

//Ventana CRUD de la tabla autor
public class VistaAutor extends JFrame {
    public JTextField txNombre = new JTextField(20);
    public JTextField txApelidos = new JTextField(20);
    public JTextField txFechaNacimiento = new JTextField(20);
    public JTextField txNacionalidad = new JTextField(20);
    public JButton btnAgregar = new JButton("Añadir");
    public DefaultListModel<Autor> modeloLlista = new DefaultListModel<>();
    public JList<Autor> listaAutores = new JList<>(modeloLlista);

    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnSalir = new JButton("Salir");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnBuscar = new JButton("Buscar por id");


    //Constructor de la ventana
    public VistaAutor() {
        setTitle("Gestion autores");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //Panel con los text fields pra itnroducir los datos
        JPanel panelUP = new JPanel(new GridLayout(4, 2, 10 ,10));
        panelUP.add(new JLabel("Nombre:"));
        panelUP.add(new JLabel("Apellidos:"));
        panelUP.add(txNombre);
        panelUP.add(txApelidos);

        panelUP.add(new JLabel("Fecha de Nacimiento:"));
        panelUP.add(new JLabel("Nacionalidad:"));
        panelUP.add(txFechaNacimiento);
        panelUP.add(txNacionalidad);

        //Panel central para mostrar la lista de los autores en todo momento
        JScrollPane scrollPane = new JScrollPane(listaAutores);

        //Panel de abajo para los botones de las operaciones del CRUD
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
