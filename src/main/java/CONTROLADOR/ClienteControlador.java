package CONTROLADOR;

import DAO.ClienteDAO;
import DAO.VehiculoDAO;
import MODELO.Cliente;
import MODELO.Vehiculo;
import UTIL.LoginApp;

import VISTA.INICIO.RegistroCliente;
import VISTA.INICIO.InicioSesionCliente;
import VISTA.INICIO.PaginaInicio;

import VISTA.MENU.CLIENTE.MenuCliente;
import VISTA.MENU.ORDEN.MenuOrden;
import VISTA.MENU.PRINCIPAL.MenuPrincipal;
import VISTA.MENU.VEHICULO.MenuVehiculo;
import VISTA.MENU.VEHICULO.RegistroVehiculo;

import static DAO.VehiculoDAO.InsertarVehiculo;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;
    private VehiculoDAO vehiculoDAO;

    // 🔥 CLIENTE LOGUEADO
    private Cliente clienteActivo;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
        vehiculoDAO = new VehiculoDAO();

        inicioVista = new PaginaInicio();

        inicioVista.getBtnRegistro().addActionListener(e -> {
            inicioVista.dispose();
            abrirRegistro();
        });

        inicioVista.getBtnInicioSesion().addActionListener(e -> {
            inicioVista.dispose();
            abrirInicioSesion();
        });
    }

    /*
    =========================
    =        REGISTRO       =
    =========================
    */
    private void abrirRegistro() {

        RegistroCliente.RegistroCliente registroVista =
                new RegistroCliente.DatosRegistroCliente();

        registroVista.getBtnRegistrar().addActionListener(e -> {
            try {
                Cliente c = registroVista.getClienteFormulario();
                c.setFechaAlta(java.time.LocalDateTime.now());

                clienteDAO.insertarCliente(c);
                registroVista.mostrarMensaje("Cliente registrado correctamente");

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
    =========================
    =     LOGIN USUARIO     =
    =========================
    */
    private void abrirInicioSesion() {

        InicioSesionCliente loginVista = new InicioSesionCliente();

        loginVista.getBtnEntrar().addActionListener(e -> {

            String usuario = loginVista.getUsuario();
            String pass = loginVista.getPassword();

            boolean loginCorrecto =
                    clienteDAO.loginCliente(usuario, pass);

            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                // 🔥 CARGAMOS EL CLIENTE COMPLETO
                clienteActivo = clienteDAO.obtenerClientePorDni(usuario);

                loginVista.dispose();
                abrirMenuPrincipal();

            } else {
                loginVista.mostrarMensaje("Usuario o contraseña incorrectos");
            }
        });

        loginVista.getBtnVolver().addActionListener(e -> {
            loginVista.dispose();
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

        RegistroVehiculo vista = new RegistroVehiculo();

        vista.getBtnGuardar().addActionListener(e -> {
            try {
                vista.dispose();
                abrirMenuVehiculo();
            } catch (Exception ex) {
            }
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            abrirMenuVehiculo();
        });
    }
}
