package VISTA;

import MODELO.Cliente;

import javax.swing.*;
import java.awt.*;

public class RegistroCliente extends VentanaBase {

    private JTextField txtDni, txtNombre, txtApellidos, txtTelefono, txtEmail, txtDireccion;
    private JButton btnRegistrar;

    public RegistroCliente() {
        super("Registro de Cliente");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formulario = new JPanel(new GridLayout(7, 2, 10, 10));

        formulario.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        formulario.add(txtDni);

        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        formulario.add(txtApellidos);

        formulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        formulario.add(txtTelefono);

        formulario.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formulario.add(txtEmail);

        formulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        btnRegistrar = new JButton("Registrar");
        formulario.add(btnRegistrar);

        JScrollPane scroll = new JScrollPane(formulario);
        add(scroll);

        setVisible(true);
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public Cliente getClienteFormulario() {
        Cliente c = new Cliente();
        c.setDni(txtDni.getText());
        c.setNombre(txtNombre.getText());
        c.setApellidos(txtApellidos.getText());
        c.setTelefono(txtTelefono.getText());
        c.setEmail(txtEmail.getText());
        c.setDireccion(txtDireccion.getText());
        return c;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}