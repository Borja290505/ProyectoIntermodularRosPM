package VISTA.MENU.VEHICULO;

import java.awt.*;
import javax.swing.*;
import DAO.ClienteDAO;
import static DAO.VehiculoDAO.InsertarVehiculo;
import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

public class AltaVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtMarca, txtModelo, txtAnio, txtKms, txtBuscarDni;
    private JComboBox<String> comboCombustible, comboColor;
    private JLabel lblClienteEncontrado;
    private Cliente clienteEncontrado;
    private JButton btnGuardar, btnVolver, btnBuscarCliente;

    public AltaVehiculo() {
        super("Alta Vehículo");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- NORTE: Título ---
        JLabel lblTitulo = new JLabel("Registrar Nuevo Vehículo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // --- CENTRO: Formulario y Validaciones ---
        JPanel panelCentral = new JPanel(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(8, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        formulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        formulario.add(txtMarca);

        formulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        formulario.add(txtModelo);

        formulario.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        formulario.add(txtAnio);

        formulario.add(new JLabel("Km actuales:"));
        txtKms = new JTextField();
        formulario.add(txtKms);

        formulario.add(new JLabel("Combustible:"));
        comboCombustible = new JComboBox<>(new String[]{"Gasolina", "Diesel", "Híbrido", "Eléctrico"});
        formulario.add(comboCombustible);

        formulario.add(new JLabel("Color:"));
        comboColor = new JComboBox<>(new String[]{"Blanco", "Negro", "Gris", "Rojo", "Azul", "Verde"});
        formulario.add(comboColor);

        formulario.add(new JLabel("DNI Propietario:"));
        txtBuscarDni = new JTextField();
        formulario.add(txtBuscarDni);

        panelCentral.add(formulario, BorderLayout.CENTER);

        // Subpanel para el resultado de búsqueda de cliente
        JPanel panelInfoCliente = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnBuscarCliente = new JButton("Validar Cliente");
        lblClienteEncontrado = new JLabel("Cliente no seleccionado");
        lblClienteEncontrado.setForeground(Color.GRAY);

        panelInfoCliente.add(btnBuscarCliente);
        panelInfoCliente.add(lblClienteEncontrado);
        panelCentral.add(panelInfoCliente, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);

        // --- SUR: Botones de Acción ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnGuardar = new JButton("Guardar Vehículo");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTOS ---

        btnBuscarCliente.addActionListener(e -> {
            String dni = txtBuscarDni.getText().trim();
            if (dni.isEmpty()) return;

            clienteEncontrado = new ClienteDAO().obtenerClientePorDni(dni);
            if (clienteEncontrado != null) {
                lblClienteEncontrado.setText("OK: " + clienteEncontrado.getNombre() + " " + clienteEncontrado.getApellidos());
                lblClienteEncontrado.setForeground(new Color(0, 120, 0));
            } else {
                lblClienteEncontrado.setText("No encontrado");
                lblClienteEncontrado.setForeground(Color.RED);
            }
        });

        btnGuardar.addActionListener(e -> {
            try {
                if (clienteEncontrado == null) {
                    JOptionPane.showMessageDialog(this, "Debes validar un cliente primero");
                    return;
                }

                Vehiculo v = new Vehiculo(
                        txtMatricula.getText().trim(),
                        txtMarca.getText().trim(),
                        txtModelo.getText().trim(),
                        Integer.parseInt(txtAnio.getText()),
                        Integer.parseInt(txtKms.getText()),
                        comboCombustible.getSelectedItem().toString(),
                        comboColor.getSelectedItem().toString(),
                        clienteEncontrado.getIdCliente()
                );

                if (InsertarVehiculo(v)) {
                    JOptionPane.showMessageDialog(this, "¡Vehículo guardado!");
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Año y KM deben ser números");
            }
        });

        btnVolver.addActionListener(e -> dispose());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnVolver() { return btnVolver; }
}
