package VISTA;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends VentanaBase {

    private JButton btnOpcionesVehiculos;
    private JButton btnOpcionesClientes;
    private JButton btnAltaCliente;
    private JButton btnBajaCliente;

    public MenuPrincipal() {
        super("Menú Principal");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 20));


        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(titulo, BorderLayout.NORTH);


        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 40, 40));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 40));


        //Texto de cada boton
        btnOpcionesVehiculos = new JButton("Opciones Vehiculos");
        btnOpcionesClientes = new JButton("Opciones Clientes");
        btnAltaCliente = new JButton("Opciones Ordenes");
        btnBajaCliente = new JButton("Totales");

        //Tamaño que tiene cada boton
        Dimension tamaño = new Dimension(180, 100);
        btnOpcionesVehiculos.setPreferredSize(tamaño);
        btnOpcionesClientes.setPreferredSize(tamaño);
        btnAltaCliente.setPreferredSize(tamaño);
        btnBajaCliente.setPreferredSize(tamaño);

        //Le damos a cada boton el mismo tipo de letra
        Font fuente = new Font("Arial", Font.BOLD, 14);
        btnOpcionesVehiculos.setFont(fuente);
        btnOpcionesClientes.setFont(fuente);
        btnAltaCliente.setFont(fuente);
        btnBajaCliente.setFont(fuente);

        //Mostramos los botones
        panelCentral.add(btnOpcionesVehiculos);
        panelCentral.add(btnOpcionesClientes);
        panelCentral.add(btnAltaCliente);
        panelCentral.add(btnBajaCliente);

        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public JButton getBtnAltaCliente() {
        return btnAltaCliente;
    }

    public void setBtnAltaCliente(JButton btnAltaCliente) {
        this.btnAltaCliente = btnAltaCliente;
    }

    public JButton getBtnBajaCliente() {
        return btnBajaCliente;
    }

    public void setBtnBajaCliente(JButton btnBajaCliente) {
        this.btnBajaCliente = btnBajaCliente;
    }

    public JButton getBtnOpcionesClientes() {
        return btnOpcionesClientes;
    }

    public void setBtnOpcionesClientes(JButton btnOpcionesClientes) {
        this.btnOpcionesClientes = btnOpcionesClientes;
    }

    public JButton getBtnOpcionesVehiculos() {
        return btnOpcionesVehiculos;
    }

    public void setBtnOpcionesVehiculos(JButton btnOpcionesVehiculos) {
        this.btnOpcionesVehiculos = btnOpcionesVehiculos;
    }
}