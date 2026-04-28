package VISTA.MENU.VEHICULO;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MODELO.Vehiculo;
import VISTA.VentanaBase;

public class ListarVehiculo extends VentanaBase {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    public ListarVehiculo() {
        super("Listado de Vehículos");

        setLayout(new BorderLayout(15, 15));

        // Título
        JLabel lblTitulo = new JLabel("Vehículos Registrados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Configuración de la tabla con todas las columnas de la BBDD
        String[] columnas = {
                "Matrícula", "Marca", "Modelo", "Año", "Kms", "Combustible", "Color", "Propietario (ID)"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable directamente
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.getTableHeader().setReorderingAllowed(false); // No permitir mover columnas

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Botón volver
        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        btnVolver = new JButton("Volver al Menú");
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cargarDatos(List<Vehiculo> vehiculos) {
        modeloTabla.setRowCount(0); // Limpiar tabla antes de cargar
        for (Vehiculo v : vehiculos) {
            Object[] fila = {
                    v.getMatricula(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnio(),
                    v.getKmsActuales(),
                    v.getCombustible(),
                    v.getColor(),
                    v.getIdCliente()
            };
            modeloTabla.addRow(fila);
        }
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
