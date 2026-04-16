package VISTA;

import javax.swing.*;
import java.awt.*;

public class MenuCliente extends VentanaBase {

    private JButton btnAltaCliente;
    private JButton btnBajaCliente;
    private JButton btnListarCliente;
    private JButton btnVolver;

    public MenuCliente() {
        super("Menú Cliente");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 20));

        JLabel titulo = new JLabel("Opciones de Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 40, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnAltaCliente = new JButton("Alta Cliente");
        btnBajaCliente = new JButton("Baja Cliente");
        btnListarCliente = new JButton("Lista Clientes");

        Dimension tamaño = new Dimension(180, 120);
        btnAltaCliente.setPreferredSize(tamaño);
        btnBajaCliente.setPreferredSize(tamaño);
        btnListarCliente.setPreferredSize(tamaño);

        btnAltaCliente.setFont(new Font("Arial", Font.BOLD, 14));
        btnBajaCliente.setFont(new Font("Arial", Font.BOLD, 14));
        btnListarCliente.setFont(new Font("Arial", Font.BOLD, 14));

        panelBotones.add(btnAltaCliente);
        panelBotones.add(btnBajaCliente);
        panelBotones.add(btnListarCliente);

        add(panelBotones, BorderLayout.CENTER);

        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        btnVolver = new JButton("Volver");
        panelVolver.add(btnVolver);

        add(panelVolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== GETTERS (OBLIGATORIOS) =====
    public JButton getBtnAltaCliente() {
        return btnAltaCliente;
    }

    public JButton getBtnBajaCliente() {
        return btnBajaCliente;
    }

    public JButton getBtnListarCliente() {
        return btnListarCliente;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}