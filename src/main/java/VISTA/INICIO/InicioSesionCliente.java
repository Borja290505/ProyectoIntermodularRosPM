package VISTA.INICIO;

import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;

public class InicioSesionCliente extends VentanaBase {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnEntrar;
    private JButton btnVolver;

    public InicioSesionCliente() {
        super("Inicio de sesión");

        JPanel formulario = new JPanel(new GridLayout(3, 2));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        formulario.add(new JLabel());

        JLabel titulo = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        add(titulo, BorderLayout.NORTH);



        formulario.add(new JLabel());

        formulario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        formulario.add(txtUsuario);

        formulario.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        formulario.add(txtPassword);

        add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));

        btnEntrar = new JButton("Entrar");
        btnVolver = new JButton("Volver");

        botones.add(btnEntrar);
        botones.add(btnVolver);

        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
