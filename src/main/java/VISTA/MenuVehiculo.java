package VISTA;

import javax.swing.*;
import java.awt.*;

public class MenuVehiculo extends VentanaBase {

    private JButton btnAltaVehiculo;
    private JButton btnBajaVehiculo;
    private JButton btnListarVehiculo;
    private JButton btnVolver;

    public MenuVehiculo() {
        super("Menú Vehículo");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(0, 20));


        JLabel titulo = new JLabel("Opciones de Vehículo", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);


        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 40, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        btnAltaVehiculo = new JButton("Alta Vehículo");
        btnBajaVehiculo = new JButton("Baja Vehículo");
        btnListarVehiculo = new JButton("Lista Vehiculos");

        Dimension tamaño = new Dimension(180, 120);
        btnAltaVehiculo.setPreferredSize(tamaño);
        btnBajaVehiculo.setPreferredSize(tamaño);
        btnListarVehiculo.setPreferredSize(tamaño);

        btnAltaVehiculo.setFont(new Font("Arial", Font.BOLD, 14));
        btnBajaVehiculo.setFont(new Font("Arial", Font.BOLD, 14));
        btnListarVehiculo.setFont(new Font("Arial",Font.BOLD,14));

        panelBotones.add(btnAltaVehiculo);
        panelBotones.add(btnBajaVehiculo);
        panelBotones.add(btnListarVehiculo);

        add(panelBotones, BorderLayout.CENTER);

        // ===== BOTÓN VOLVER =====
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        btnVolver = new JButton("Volver");
        panelVolver.add(btnVolver);

        add(panelVolver, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== GETTERS (OBLIGATORIOS) =====
    public JButton getBtnAltaVehiculo() {
        return btnAltaVehiculo;
    }

    public JButton getBtnBajaVehiculo() {
        return btnBajaVehiculo;
    }

    public JButton getBtnListarVehiculo() {
        return btnListarVehiculo;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }


}
