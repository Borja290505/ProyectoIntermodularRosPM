package VISTA.MENU.VEHICULO;

import MODELO.Vehiculo;
import VISTA.VentanaBase;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarVehiculo extends VentanaBase {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarVehiculo() {
        super("Listado de Vehículos");
        setLayout(new BorderLayout(10, 10));

        // Configuración de la tabla
        String[] columnas = {"Matrícula", "Bastidor", "Marca", "Modelo", "Año", "Kms", "ID Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botón volver
        btnVolver = new JButton("Volver al Menú");
        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void cargarDatos(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Vehiculo v : vehiculos) {
            Object[] fila = {
                    v.getMatricula(), v.getBastidor(), v.getMarca(),
                    v.getModelo(), v.getAnio(), v.getKmsActuales(), v.getCliente()
            };
            modeloTabla.addRow(fila);
        }
    }

    public JButton getBtnVolver() { return btnVolver; }
}