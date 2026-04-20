package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.VehiculoDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.PaginaInicio;

import VISTA.MENU.CLIENTE.MenuCliente;
import VISTA.MENU.ORDEN.MenuOrden;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;
import VISTA.MENU.VEHICULO.BajaVehiculo;
import VISTA.MENU.VEHICULO.ListarVehiculo;
import VISTA.MENU.VEHICULO.MenuVehiculo;
import VISTA.MENU.VEHICULO.AltaVehiculo;

import javax.swing.*;
import java.util.List;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;
    private VehiculoDAO vehiculoDAO;
    private Cliente clienteActivo;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
        vehiculoDAO = new VehiculoDAO();

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
                JOptionPane.showMessageDialog(
                        inicioVista,
                        "Usuario o contraseña incorrectos"
                );
            }
        });


        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            abrirRegistro();
        });
    }

    /*
    =========================
    =        REGISTRO       =
    =========================
    */
    private void abrirRegistro() {

        RegistroCliente registroVista =
                new RegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");
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

    /*
    ========================
    =    MENÚ PRINCIPAL    =
    ========================
    */
    private void abrirMenuPrincipal() {

        MenuPrincipal menu =
                new MenuPrincipal(clienteActivo.getDni());

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

    /*
    =========================
    =     MENÚ VEHICULO     =
    =========================
    */
    private void abrirMenuVehiculo() {

        MenuVehiculo menuVehiculo = new MenuVehiculo();

        // 🔥 ALTA VEHÍCULO
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

        menuVehiculo.getBtnVolver().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }

    /*
    ==========================
    =      MENÚ CLIENTE      =
    ==========================
    */
    private void abrirMenuCliente() {

        MenuCliente menuCliente = new MenuCliente();

        menuCliente.getBtnVolver().addActionListener(e -> {
            menuCliente.dispose();
            abrirMenuPrincipal();
        });
    }

    /*
    ==========================
    =      MENÚ ORDENES      =
    ==========================
    */
    private void abrirMenuOrdenes(){

        MenuOrden menuOrden = new MenuOrden();

        menuOrden.getBtnVolver().addActionListener(e -> {
            menuOrden.dispose();
            abrirMenuPrincipal();
        });
    }

    /*
    ==========================
    =     ALTA VEHÍCULO      =
    ==========================
    */
    private void abrirAltaVehiculo(){

        AltaVehiculo altaVehiculo = new AltaVehiculo();

        altaVehiculo.getBtnGuardar().addActionListener(e -> {
            try {
                altaVehiculo.dispose();
                abrirMenuVehiculo();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        altaVehiculo.getBtnVolver().addActionListener(e -> {
            altaVehiculo.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirBajaVehiculo(){
        BajaVehiculo bajaVehiculo = new BajaVehiculo();

        bajaVehiculo.getBtnEliminar().addActionListener(e -> {
            String matricula = bajaVehiculo.getTxtMatricula().getText().trim();

            if (VehiculoDAO.EliminarVehiculo(matricula)) {
                JOptionPane.showMessageDialog(bajaVehiculo, "Vehículo eliminado con éxito");
                bajaVehiculo.getTxtMatricula().setText("");
            } else {
                JOptionPane.showMessageDialog(bajaVehiculo, "No se encontró la matrícula: " + matricula);
            }

        });

        bajaVehiculo.getBtnVolver().addActionListener(e -> {
            bajaVehiculo.dispose();
            abrirMenuVehiculo();
        });
    }

    private void abrirListarVehiculos() {
        ListarVehiculo vistaLista = new ListarVehiculo();

        // Cargamos los datos desde el DAO
        List<Vehiculo> lista = VehiculoDAO.ListarVehiculos();
        vistaLista.cargarDatos(lista);

        vistaLista.getBtnVolver().addActionListener(e -> {
            vistaLista.dispose();
            abrirMenuVehiculo();
        });

        vistaLista.setVisible(true);
    }
}
