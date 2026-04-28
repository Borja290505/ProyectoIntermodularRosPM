package VISTA.MENU.CLIENTE;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;
import DAO.ClienteDAO;
import MODELO.Cliente;
import VISTA.VentanaBase;

public class AltaCliente extends VentanaBase {

    private JTextField txtDni, txtNombre, txtApellidos, txtTelefono, txtDireccion, txtEmail;
    private JButton btnGuardar, btnVolver;

    public AltaCliente() {
        super("Registrar Nuevo Cliente");
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Alta de Cliente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario (6 filas ahora)
        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 20));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        formulario.add(new JLabel("DNI:"));      txtDni = new JTextField(); formulario.add(txtDni);
        formulario.add(new JLabel("Nombre:"));   txtNombre = new JTextField(); formulario.add(txtNombre);
        formulario.add(new JLabel("Apellidos:")); txtApellidos = new JTextField(); formulario.add(txtApellidos);
        formulario.add(new JLabel("Teléfono:"));  txtTelefono = new JTextField(); formulario.add(txtTelefono);
        formulario.add(new JLabel("Dirección:")); txtDireccion = new JTextField(); formulario.add(txtDireccion);
        formulario.add(new JLabel("Email:"));     txtEmail = new JTextField(); formulario.add(txtEmail);

        add(formulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnGuardar = new JButton("Guardar Cliente");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            if (txtDni.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "DNI y Nombre son obligatorios");
                return;
            }

            Cliente c = new Cliente();
            c.setDni(txtDni.getText().trim());
            c.setNombre(txtNombre.getText().trim());
            c.setApellidos(txtApellidos.getText().trim());
            c.setTelefono(txtTelefono.getText().trim());
            c.setDireccion(txtDireccion.getText().trim());
            c.setEmail(txtEmail.getText().trim());
            c.setContraseña(""); // Se deja vacía o se puede poner el DNI como clave inicial
            c.setFechaAlta(LocalDateTime.now());

            if (new ClienteDAO().insertarCliente(c)) {
                JOptionPane.showMessageDialog(this, "¡Cliente guardado con éxito!");
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnGuardar() { return btnGuardar; }
}
