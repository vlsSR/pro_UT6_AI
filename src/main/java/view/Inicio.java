package view;

import javax.swing.*;
import java.awt.*;

//Ventana de inicio del programa, te permite navegar entre las diferentes tablas o salir
public class Inicio extends JFrame {

    public JButton btnLibro = new JButton("Libros");
    public JButton btnAutor = new JButton("Autores");
    public JButton btnSalir = new JButton("Salir");
    public JLabel lblTitulo = new JLabel("Bienvenido a la libreria", SwingConstants.CENTER);
    public JLabel lblTitulo2 = new JLabel("Selecciona la tabla a la que quieres acceder", SwingConstants.CENTER);
    private JPanel panelBtns = new JPanel(new FlowLayout());
    private JPanel panelTitl = new JPanel(new GridLayout(2,1));

    //Constructor de la ventana con sus propiedades
    public Inicio() {
        setTitle("CRUD Libreria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setResizable(false);
        setSize(300, 200);

        panelTitl.add(lblTitulo);
        panelTitl.add(lblTitulo2);

        panelBtns.add(btnLibro);
        panelBtns.add(btnAutor);
        panelBtns.add(btnSalir);

        add(panelTitl);
        add(panelBtns);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
