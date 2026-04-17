package VISTA.INICIO;

import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class PaginaInicio extends VentanaBase {

    private JButton btnRegistro;
    private JButton btnInicioSesion;

    public PaginaInicio() {
        super("Inicio");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 15));

        JLabel titulo1 = new JLabel("BIENVENIDO A ", SwingConstants.CENTER);
        titulo1.setFont(new Font("Arial", Font.BOLD, 26));


        JLabel titulo2 = new JLabel("ROS POWER MOTOR", SwingConstants.CENTER);
        titulo2.setFont(new Font("Arial", Font.BOLD, 36));
        titulo2.setForeground(Color.RED);


        btnInicioSesion = new JButton("Inicio sesion");
        btnRegistro = new JButton("Registro");

        panel.add(titulo1);
        panel.add(titulo2);
        panel.add(new JLabel());
        panel.add(btnInicioSesion);
        panel.add(btnRegistro);


        add(panel);

        setVisible(true);
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    public JButton getBtnInicioSesion() {
        return btnInicioSesion;
    }
}
