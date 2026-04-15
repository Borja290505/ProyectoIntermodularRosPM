package CONTROLADOR;

import DAO.ClienteDAO;
import MODELO.Cliente;
import UTIL.LoginApp;
import VISTA.*;

public class ClienteControlador {

    private PaginaInicio inicioVista;
    private ClienteDAO clienteDAO;

    public ClienteControlador() {

        clienteDAO = new ClienteDAO();
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

    // =========================
    // REGISTRO
    // =========================
    private void abrirRegistro() {

        RegistroCliente registroVista = new RegistroCliente();

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

    // =========================
    // LOGIN
    // =========================
    private void abrirInicioSesion() {

        InicioSesionCliente loginVista = new InicioSesionCliente();

        loginVista.getBtnEntrar().addActionListener(e -> {

            String usuario = loginVista.getUsuario();
            String pass = loginVista.getPassword();

            boolean loginCorrecto = clienteDAO.loginCliente(usuario, pass);

            LoginApp.registrarLogin(usuario, loginCorrecto);

            if (loginCorrecto) {
                loginVista.dispose();
                abrirMenuPrincipal();   // ✅ CAMBIO CLAVE
            } else {
                loginVista.mostrarMensaje("Usuario o contraseña incorrectos");
            }
        });

        loginVista.getBtnVolver().addActionListener(e -> {
            loginVista.dispose();
            new ClienteControlador();
        });
    }

    // =========================
    // MENÚ PRINCIPAL
    // =========================
    private void abrirMenuPrincipal() {

        MenuPrincipal menu = new MenuPrincipal();

        // Botón opciones / alta vehículo
        menu.getBtnAltaVehiculo().addActionListener(e -> {
            menu.dispose();
            abrirMenuVehiculo();
        });
    }

    // =========================
    // MENÚ VEHÍCULOS
    // =========================
    private void abrirMenuVehiculo() {

        MenuVehiculo menuVehiculo = new MenuVehiculo();

        menuVehiculo.getBtnVolver().addActionListener(e -> {
            menuVehiculo.dispose();
            abrirMenuPrincipal();
        });
    }
}