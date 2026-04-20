package VISTA.MENU.VEHICULO;

import DAO.ClienteDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import VISTA.VentanaBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static DAO.VehiculoDAO.InsertarVehiculo;

public class AltaVehiculo extends VentanaBase {

    private JTextField txtMatricula, txtBastidor, txtMarca, txtModelo, txtAnio, txtKms;
    private JComboBox<Cliente> comboClientes;
    private JButton btnGuardar, btnVolver;


    public AltaVehiculo() {
        super("Alta Vehículo");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(7, 2, 15, 15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        formulario.add(new JLabel("Nº Bastidor:"));
        txtBastidor = new JTextField();
        formulario.add(txtBastidor);

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

        formulario.add(new JLabel("Cliente:"));
        comboClientes = new JComboBox<>();
        cargarClientes();
        formulario.add(comboClientes);

        add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnVolver = new JButton("Volver");
        botones.add(btnGuardar);
        botones.add(btnVolver);


        btnGuardar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = txtMatricula.getText().trim();
                String nBastidor = txtBastidor.getText().trim();
                String marca = txtMarca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int anio = Integer.parseInt(txtAnio.getText());
                int km = Integer.parseInt(txtKms.getText());
                int idCLiente = comboClientes.getItemCount();


                Vehiculo v = new Vehiculo(matricula, nBastidor, marca, modelo, anio, km, idCLiente);


                if (InsertarVehiculo(v)) {
                    JOptionPane.showMessageDialog(null, "Insertado correctamente");
                    return;
                }
            }
        });

        add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // =========================
    // CARGAR CLIENTES EN COMBO
    // =========================
    private void cargarClientes() {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarClientes();
        for (Cliente c : clientes) {
            comboClientes.addItem(c);
        }
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}