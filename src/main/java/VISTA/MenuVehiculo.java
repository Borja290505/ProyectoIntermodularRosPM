package VISTA;

import javax.swing.*;
import java.awt.*;

public class OpcionesVehiculos extends VentanaBase{
    public OpcionesVehiculos(){
        super("Opciones Vehiculos");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 20));


        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);
    }
}
