package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.VehiculoDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.PaginaInicio;
import VISTA.MENU.CLIENTE.AltaCliente;
import VISTA.MENU.CLIENTE.MenuCliente;
import VISTA.MENU.ORDEN.MenuOrden;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;
import VISTA.MENU.VEHICULO.*;

import javax.swing.*;
import java.util.List;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;
    private Cliente clienteActivo;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
        inicioVista = new PaginaInicio();


        inicioVista.getBtnInicioSesion().addActionListener(e -> {
            String usuario = inicioVista.getTxtUsuario().getText().trim();
            String pass = new String(inicioVista.getTxtPassword().getPassword()).trim();

            boolean loginCorrecto = clienteDAO.loginCliente(usuario, pass);
            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                clienteActivo = clienteDAO.obtenerClientePorDni(usuario);
                inicioVista.dispose();
                abrirMenuPrincipal();
            } else {
                JOptionPane.showMessageDialog(inicioVista,
                        "Usuario o contraseña incorrectos");
            }
        });

        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            abrirRegistro();
        });
    }

    // =========================
    // REGISTRO CLIENTE
    // =========================
    private void abrirRegistro() {

        RegistroCliente registroVista = new RegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");
                registroVista.dispose();
                new ClienteControlador();

            } catch (Exception ex) {
                registroVista.mostrarMensaje("Error al registrar cliente");
            }
        });

        registroVista.getBtnVolver().addActionListener(e -> {
            registroVista.dispose();
            new ClienteControlador();
        });
    }

    // =========================
    // MENÚ PRINCIPAL
    // =========================
    private void abrirMenuPrincipal() {

        MenuPrincipal menu = new MenuPrincipal(clienteActivo.getDni());

        menu.getBtnVehiculos().addActionListener(e -> {
            menu.dispose();
            abrirMenuVehiculo();
        });

        menu.getBtnClientes().addActionListener(e -> {
            menu.dispose();
            abrirMenuCliente();
        });

        menu.getBtnOrdenes().addActionListener(e -> {
            menu.dispose();
            abrirMenuOrdenes();
        });
    }

    // =========================
    // MENÚ VEHÍCULO
    // =========================
    private void abrirMenuVehiculo() {

        MenuVehiculo menuVehiculo = new MenuVehiculo();

        menuVehiculo.getBtnAltaVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirAltaVehiculo();
        });

        menuVehiculo.getBtnBajaVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirBajaVehiculo();
        });

        menuVehiculo.getBtnListarVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirListarVehiculos();
        });

        menuVehiculo.getBtnModificarVehiculo().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirModificarVehiculo();
        });

        menuVehiculo.getBtnVolver().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ CLIENTE
    // =========================
    private void abrirMenuCliente() {

        MenuCliente menuCliente = new MenuCliente();

        menuCliente.getBtnAltaCliente().addActionListener(e -> {
            menuCliente.dispose();
            abrirAltaCliente();
        });

        menuCliente.getBtnVolver().addActionListener(e -> {
            menuCliente.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // MENÚ ÓRDENES
    // =========================
    private void abrirMenuOrdenes() {

        MenuOrden menuOrden = new MenuOrden();

        menuOrden.getBtnVolver().addActionListener(e -> {
            menuOrden.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // ALTA VEHÍCULO
    // =========================
    private void abrirAltaVehiculo() {


        AltaVehiculo altaVehiculo = new AltaVehiculo();

        altaVehiculo.getBtnVolver().addActionListener(e -> {
            altaVehiculo.dispose();
            abrirMenuVehiculo();
        });

        altaVehiculo.getBtnGuardar().addActionListener(e -> {
            altaVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    // =========================
    // BAJA VEHÍCULO
    // =========================
    private void abrirBajaVehiculo() {

        BajaVehiculo bajaVehiculo = new BajaVehiculo();

        bajaVehiculo.getBtnEliminar().addActionListener(e -> {
            String matricula = bajaVehiculo.getTxtMatricula().getText().trim();
            if (VehiculoDAO.EliminarVehiculo(matricula)) {

                JOptionPane.showMessageDialog(bajaVehiculo,
                        "Vehículo eliminado con éxito");
                bajaVehiculo.getTxtMatricula().setText("");

            } else {
                JOptionPane.showMessageDialog(bajaVehiculo,
                        "No se encontró la matrícula: " + matricula);
            }
            bajaVehiculo.dispose();
            abrirMenuPrincipal();
        });

        bajaVehiculo.getBtnVolver().addActionListener(e -> {
            bajaVehiculo.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // LISTAR VEHÍCULOS
    // =========================
    private void abrirListarVehiculos() {

        ListarVehiculo vistaLista = new ListarVehiculo();
        List<Vehiculo> lista = VehiculoDAO.ListarVehiculos();

        vistaLista.cargarDatos(lista);

        vistaLista.getBtnVolver().addActionListener(e -> {
            vistaLista.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // MODIFICAR VEHÍCULO
    // =========================
    private void abrirModificarVehiculo() {

        ModificarVehiculo vistaModificar = new ModificarVehiculo();

        // Acción del botón BUSCAR
        vistaModificar.getBtnBuscar().addActionListener(e -> {
            String matricula = vistaModificar.getTxtMatricula().getText().trim();

            // Usamos el método estático del DAO
            Vehiculo v = VehiculoDAO.obtenerVehiculoPorMatricula(matricula);

            if (v != null) {
                vistaModificar.rellenarCampos(v);
                vistaModificar.setCamposEditables(true);
                vistaModificar.getTxtMatricula().setEditable(false); // Bloqueamos ID
            } else {
                JOptionPane.showMessageDialog(vistaModificar,
                        "No se encontró ningún vehículo con esa matrícula.");
            }
        });

        // Acción del botón GUARDAR (Modificar)
        vistaModificar.getBtnModificar().addActionListener(e -> {
            try {
                // Capturamos los datos de la vista
                String matricula = vistaModificar.getTxtMatricula().getText();
                String marca = vistaModificar.getTxtMarca().getText();
                String modelo = vistaModificar.getTxtModelo().getText();
                int anio = Integer.parseInt(vistaModificar.getTxtAnio().getText());
                int kms = Integer.parseInt(vistaModificar.getTxtKms().getText());

                // Obtenemos el cliente seleccionado del ComboBox
                Cliente c = (Cliente) vistaModificar.getComboClientes().getSelectedItem();

                if (c == null) {
                    JOptionPane.showMessageDialog(vistaModificar, "Debes seleccionar un propietario.");
                    return;
                }

                // Creamos el objeto vehículo con los nuevos datos
                Vehiculo v = new Vehiculo(matricula, marca, modelo, anio, kms, "Gasolina", "Blanco", c.getIdCliente());

                // Llamamos al DAO para actualizar en la BD
                if (VehiculoDAO.ModificarVehiculo(v)) {
                    JOptionPane.showMessageDialog(vistaModificar, "Vehículo actualizado con éxito.");
                    vistaModificar.dispose();
                    abrirMenuVehiculo();
                } else {
                    JOptionPane.showMessageDialog(vistaModificar, "Error al actualizar en la base de datos.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaModificar, "Año y Kilómetros deben ser números válidos.");
            }
        });

        // Acción del botón VOLVER
        vistaModificar.getBtnVolver().addActionListener(e -> {
            vistaModificar.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // LÓGICA DE CLIENTES
    // =========================
    private void abrirAltaCliente() {
        AltaCliente vista = new AltaCliente();
        vista.getBtnVolver().addActionListener(e -> { vista.dispose(); abrirMenuCliente(); });
    }
}